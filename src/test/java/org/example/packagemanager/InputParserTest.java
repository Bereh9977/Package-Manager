package org.example.packagemanager;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InputParserTest
{
    @Test
    void shouldParseValidInput()
            throws PackageManagerException
    {
        InputParser parser = new InputParser();

        List<String> lines = List.of(
                "package: app, v1.0",
                "requires: core, v1.0",
                "package: core, v1.0"
        );

        DependencyGraph graph = parser.parse(lines);

        assertNotNull(graph);
        assertEquals(2, graph.getGraph().size());
    }
}
