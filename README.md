# Puzzalarm

COP4020 - Software Engineering 

Semester Project

- [X] Add alarms into recyclerview on alarms page
- [ ] Persistent alarms
- [X] Puzzles
- [X] UI
- [X] Disable back button
- [X] Sort alarms in recyclerview
- [X] Add 0 before <10 time
- [X] Remove alarms
- [X] Add more games
- [X] Nice README, documentation, and Github labels0
- [X] Testing

Bugs:

- Alarms may ring multiple times if, in the same minute, there is a context change. (Caused by a lack of proper thread handling)

- In undetermined circumstances, the alarm may cause the chosen puzzle to appear twice forcing the user to solve two of the same puzzle type. (Very rare, Cause unknown)

- In undetermined circumstances, an alarm will not actually be deleted. It seems to only happen on alarms that have rang and likely has to do with the thread continuing to run for a short time. (Specific cause unknown)
