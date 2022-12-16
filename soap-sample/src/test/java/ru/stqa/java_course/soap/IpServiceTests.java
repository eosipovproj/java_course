package ru.stqa.java_course.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

public class IpServiceTests {
    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("92.1023210.191.64");
        System.out.println(ipLocation);

    }
}
