package ru.stqa.java_course.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.java_course.mantis.model.Users;

public class ChangePasswordTests extends TestBase{
    @Test
    public void testChangePassword(){
        Users users = app.db().users();
        int userId= users.iterator().next().getId();
    }
}
