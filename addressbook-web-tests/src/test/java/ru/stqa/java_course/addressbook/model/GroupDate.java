package ru.stqa.java_course.addressbook.model;

public class GroupDate {
    private int id;
    private final String groupname;
    private final String header;
    private final String footer;

    public GroupDate(int id, String name, String header, String footer) {
        this.id = id;
        this.groupname = name;
        this.header = header;
        this.footer = footer;
    }

    @Override
    public int hashCode() {
        return groupname != null ? groupname.hashCode() : 0;
    }

    public GroupDate(String name, String header, String footer) {
        this.id = Integer.MAX_VALUE;
        this.groupname = name;
        this.header = header;
        this.footer = footer;
    }

    public String getGroupname() {
        return groupname;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GroupDate{" +
                "id='" + id + '\'' +
                ", groupname='" + groupname + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupDate groupDate = (GroupDate) o;

        return groupname != null ? groupname.equals(groupDate.groupname) : groupDate.groupname == null;
    }
}
