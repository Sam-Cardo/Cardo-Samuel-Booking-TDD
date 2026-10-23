# NOTES

## Gaps

| Rule      | What was unclear                                                | Ruling                                         | My test for it |
| ----      |  ----------------------------------------------                 | ---------------- | ------ | --------------     |   Alice books R1 10–12, then Bob books R1 11–13
| R4/ R5    |  Gap : R4/R5 With the rule stating that overlapping can't       | I think so. surely identical timeslots overlap |   Alice books R1 10–11, then R2 10–11
|           |  happen but what if two bookings with the exact same start time |                                                | 
|           |  and end time would that count as overlapping?                  |                                                | 
|           |                                                                 |                                                | 
|  R2       | Gap: R2 - Is a slot starting exactly now allowed?               |  I don't think so: now is not in the future    |     Booking 08:00–09:00


## AI corrections 

### 1
- What I asked: how to import the room class into my tests.
- What it produced: an import statement using main.java.room.
- What was wrong: the suggusted import my project was structured. 
- The test that caught it: the code couldn't compile because the import wasn't able to be resolved.

### 2
- What I asked: How to make two different objects compare correctly for .equals.
- What it produced: it produced an equals method that compared the booking fields.
- What was wrong: it didn't the correct java signature for .equals function making it not able to compare nicely.
- The test that caught it: it compared it but it failed due to having the same values.

### 3
- What I asked: to fix my incorrect overlapping logic.
- What it produced: the check didnt handle the two boundries of two time slots.
- What was wrong: Two different bookings touch each other like 10-11 and 11-12 should be allowed because the booking is overlapping.
- The test that caught it: the boundry test looked at futher to make sure its allowed.