package org.example.packagemanager;

public class PackageId
{
    private String name;
    private String version;

    public PackageId(String name, String version)
    {
        this.name = name;
        this.version = version;
    }

    public String getName()
    {
        return name;
    }

    public String getVersion()
    {
        return version;
    }
}
