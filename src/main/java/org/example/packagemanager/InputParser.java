package org.example.packagemanager;

import java.util.List;

public class InputParser
{
    public DependencyGraph parse(List<String> lines)
    {

        DependencyGraph graph = new DependencyGraph();
        PackageId currentPackage = null;

        for (String line : lines)
        {

            line = line.trim();

            if (line.startsWith("package:"))
            {

                String[] parts = line.substring(8).split(",");
                String name = parts[0].trim();
                String version = parts[1].trim();

                currentPackage = new PackageId(name, version);
                graph.addPackage(currentPackage);

            } else if (line.startsWith("requires:"))
            {

                String[] parts = line.substring(9).split(",");
                String name = parts[0].trim();
                String version = parts[1].trim();

                PackageId dependency = new PackageId(name, version);
                graph.addDependency(currentPackage, dependency);
            }
        }

        return graph;
    }
}
