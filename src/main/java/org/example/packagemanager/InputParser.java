package org.example.packagemanager;

import java.util.List;
import java.util.regex.Pattern;

public class InputParser
{
    private static final Pattern VERSION_PATTERN = Pattern.compile("v\\d+\\.\\d+");

    public DependencyGraph parse(List<String> lines) throws PackageManagerException
    {
        DependencyGraph graph = new DependencyGraph();
        PackageId currentPackage = null;

        for (int i = 0; i < lines.size(); i++)
        {
            String line = lines.get(i).trim();
            int lineNumber = i + 1;

            if (line.isEmpty())
            {
                continue;
            }

            if (line.startsWith("package:"))
            {
                currentPackage = parsePackageLine(
                        line,
                        lineNumber,
                        "package:"
                );
                graph.addPackage(currentPackage);
            }
            else if (line.startsWith("requires:"))
            {
                if (currentPackage == null)
                {
                    throw new InvalidFormatException(
                            "'requires:' cannot appear before 'package:'",
                            lineNumber
                    );
                }

                PackageId dependency = parsePackageLine(
                        line,
                        lineNumber,
                        "requires:"
                );

                graph.addDependency(currentPackage, dependency);
            }
            else
            {
                throw new InvalidFormatException(
                        "Unknown line format: " + line,
                        lineNumber
                );
            }
        }

        return graph;
    }

    private PackageId parsePackageLine(
            String line,
            int lineNumber,
            String prefix
    ) throws InvalidFormatException, InvalidVersionException
    {
        String content = line.substring(prefix.length()).trim();
        String[] parts = content.split(",");

        if (parts.length != 2)
        {
            throw new InvalidFormatException(
                    "Expected format: " + prefix + " <name>, <version>",
                    lineNumber
            );
        }

        String name = parts[0].trim();
        String version = parts[1].trim();

        if (name.isEmpty())
        {
            throw new InvalidFormatException(
                    "Package name cannot be empty",
                    lineNumber
            );
        }

        if (!VERSION_PATTERN.matcher(version).matches())
        {
            throw new InvalidVersionException(
                    "Invalid version format: " + version,
                    lineNumber
            );
        }

        return new PackageId(name, version);
    }
}