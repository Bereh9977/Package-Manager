package org.example.packagemanager;

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
        System.out.println("Input file: " + filePath);
    }
}
