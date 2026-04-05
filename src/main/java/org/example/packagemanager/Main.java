package org.example.packagemanager;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Usage: java -jar app.jar <input-file>");
            return;
        }

        String filePath = args[0];

        try
        {
            InputReader reader = new InputReader();
            List<String> lines = reader.readFile(filePath);

            InputParser parser = new InputParser();
            DependencyGraph graph = parser.parse(lines);

            ValidationEngine validator = new ValidationEngine();

            if (validator.hasCycle(graph))
            {
                ReportGenerator report = new ReportGenerator();
                report.printCycleError();
            }
            else
            {
                ResolverEngine resolver = new ResolverEngine();
                List<PackageId> order = resolver.topologicalSort(graph);

                ReportGenerator report = new ReportGenerator();
                report.printInstallOrder(order);
            }
        }
        catch (PackageManagerException e)
        {
            printFormattedError(e);
        }
        catch (Exception e)
        {
            System.out.println(
                    "Error: UnexpectedException - " + e.getMessage()
            );
        }
    }

    private static void printFormattedError(PackageManagerException exception)
    {
        String errorType = exception.getClass().getSimpleName();
        String message = exception.getMessage();
        int lineNumber = exception.getLineNumber();

        if (lineNumber > 0)
        {
            System.out.println(
                    "Error: "
                            + errorType
                            + " - "
                            + message
                            + " at line "
                            + lineNumber
            );
        }
        else
        {
            System.out.println(
                    "Error: "
                            + errorType
                            + " - "
                            + message
            );
        }
    }
}
