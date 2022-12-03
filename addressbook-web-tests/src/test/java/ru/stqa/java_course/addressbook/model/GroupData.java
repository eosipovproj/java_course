package ru.stqa.java_course.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class GroupData {
    @Id
    @Column(name = "group_id")
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    @Column(name = "group_name")
    @Expose
    private String groupname;
    @Type(type = "text")
    @Column(name = "group_header")
    @Expose
    private String header;
    @Type(type = "text")
    @Column(name = "group_footer")
    @Expose
    private String footer;

    @ManyToMany(mappedBy = "groups")
    private Set<ContactData> contacts = new HashSet<ContactData>();

    public int getId(){
        return id;
    }
    public GroupData withId(int id) {
        this.id = id;
        return this;
    }
    public GroupData withGroupname(String groupname) {
        this.groupname = groupname;
        return this;
    }
    public GroupData withHeader(String header) {
        this.header = header;
        return this;
    }
    public GroupData withFooter(String footer) {
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
    public Contacts getContacts() {
        return new Contacts(contacts);
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

        GroupData that = (GroupData) o;

        if (id != that.id) return false;
        if (groupname != null && that.groupname != null && !Objects.equals(groupname, that.groupname)) { return false; }
        if (header != null && that.header != null && !Objects.equals(header, that.header)) { return false; }
        if (footer != null && that.footer != null && !Objects.equals(footer, that.footer)) { return false; }
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (groupname != null ? groupname.hashCode() : 0);
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (footer != null ? footer.hashCode() : 0);
        return result;
    }
}
