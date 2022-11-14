package ru.stqa.java_course.addressbook.model;

public class GroupDate {
    private int id = Integer.MAX_VALUE;
    private String groupname;
    private String header;
    private String footer;

    public int getId(){
        return id;
    }
    public GroupDate withId(int id) {
        this.id = this.id;
        return this;
    }
    public GroupDate withGroupname(String groupname) {
        this.groupname = groupname;
        return this;
    }
    public GroupDate withHeader(String header) {
        this.header = header;
        return this;
    }
    public GroupDate withFooter(String footer) {
        this.footer = footer;
        return this;
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

        if (id != groupDate.id) return false;
        return groupname != null ? groupname.equals(groupDate.groupname) : groupDate.groupname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (groupname != null ? groupname.hashCode() : 0);
        return result;
    }
}
