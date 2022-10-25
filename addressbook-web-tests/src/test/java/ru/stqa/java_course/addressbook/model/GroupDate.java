package ru.stqa.java_course.addressbook.model;

public class GroupDate {
    private final String groupname;
    private final String header;
    private final String footer;

    public GroupDate(String name, String header, String footer) {
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
}
