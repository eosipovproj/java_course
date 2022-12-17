package ru.stqa.java_course.rest.tests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class restTests extends TestBase{

    @Test
    public void testCreateIssue () throws IOException {
        skipIfNotFixed(56);
        Set<Issue> oldIssues = app.json().getIssue();
        Issue newIssue = new Issue().withSubject("Test 9.45 issue").withDescription("New test99 issue");
        int issueId = app.json().createIssue (newIssue);
        Set<Issue> newIssues = app.json().getIssue();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }
}
