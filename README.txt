1. Fix the path to you chromedriver in /test/resources/conf.properties
2. Test data is in testdata.json file. You can change/add something, if you like.
Just don't remove any fields and don't change values formats or it wont parse properly anymore.
3. Run test from RunTestng file if you want reports

About reports though. TestNG default reports seem fairly readable to me.
I mean they already have test title/status/parameters and error stack trace.
I also made testng add screenshots on test fail just in case.
But if you guys need more detailed reports, let me know, just not sure what you mean by "human readable".