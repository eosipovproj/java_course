package ru.stqa.java_course;

import com.google.common.collect.ImmutableBiMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithibTests {
    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("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");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("eosipovproj", "java_course")).commits();
        for (RepoCommit commit: commits.iterate(new ImmutableBiMap.Builder<String, String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }

    }
}
