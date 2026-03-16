package org.example.packagemanager;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;
import java.util.ArrayList;

public class ResolverEngine
{
    public List<PackageId> topologicalSort(DependencyGraph graph)
    {

        Set<PackageId> visited = new HashSet<>();
        Stack<PackageId> stack = new Stack<>();

        for (PackageId pkg : graph.getGraph().keySet())
        {
            if (!visited.contains(pkg))
            {
                dfs(pkg, graph, visited, stack);
            }
        }

        List<PackageId> result = new ArrayList<>();

        while (!stack.isEmpty())
        {
            result.add(stack.pop());
        }

        return result;
    }

    private void dfs(
            PackageId pkg,
            DependencyGraph graph,
            Set<PackageId> visited,
            Stack<PackageId> stack)
    {

        visited.add(pkg);

        for (PackageId dep : graph.getDependencies(pkg))
        {
            if (!visited.contains(dep))
            {
                dfs(dep, graph, visited, stack);
            }
        }

        stack.push(pkg);
    }
}
