<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >

<suite name="SeleniumGoogleTest" verbose="1">
    <test name="TestGoogleSearch">
        <parameter name="searchKey" value="grid dynamics" />
        <parameter name="searchHeaderSubstring" value="grid dynamics" />
        <classes>
            <class name = "GoogleSearchTest" />
        </classes>
    </test>
</suite>