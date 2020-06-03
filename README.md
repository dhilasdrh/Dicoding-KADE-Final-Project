# KADE-submission5
This is my submission for the final project in Dicoding "Kotlin Android Developer Expert" course. For this submission we have to build an Android application project that utilizes all the materials that we've learned at this academy. To be able to graduate from this academy, you must create a Football Apps application with several predetermined criteria.

## Application Criteria

Some features that must be added to the application:

* **Match standings** : Displays the match standings table for the selected league.
* **Team list** : Displays a list of teams in the selected league.
* **Team Details** : Displays team information (name, logo and team details) based on the selected team.
* **Team Search** : Add features to do team searches.
* **Favorite Team** : Add features to accommodate favorite teams with criteria:

  * Can add teams to the favorite database.
  * Can delete the team from the favorite database.
  * Showing list of favorite teams.

**Some optional features that you can add:**

* **Player List** : Displays a list of players on the selected team.
* **Player Details** : Player details page that displays the name, fan art and other information of the selected player.

## Notes
* You are free to be creative in making this application. The appearance of the application does not have to be the same as the reference given.
* You are free to use third-party networking libraries, for example Retrofit, Rx, etc.
* You are free to use any library database such as Realm, Room, etc.
* In compiling views it is allowed to use Anko Layout or XML.


### The opportunity for your submission to be accepted will be greater if:
* Make maximum use of data obtained from the API to be displayed into the application.
* UI in the application is interesting and uses the concept of material design.
* Use patterns like MVP, MVVM, Architecture Component or others.
* Apply unit and instrumentation tests.
* Write the code clean.

### Resources

Here are the API resources that you can use to do this final project:

* **League details**: https://www.thesportsdb.com/api/v1/json/1/lookupleague.php?id={idLeague}
* **Match standings**: https://www.thesportsdb.com/api/v1/json/1/lookuptable.php?l={idLeague}
* **List of teams**: https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id={idLeague}
* **Team details**: https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id={idTeam}
* **List of players**: https://www.thesportsdb.com/api/v1/json/1/lookup_all_players.php?id={idTeam}
* **Player details**: https://www.thesportsdb.com/api/v1/json/1/lookupplayer.php?id={idPlayer}
* **Next match list**:
  * https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id={idLeague}
  * https://www.thesportsdb.com/api/v1/json/1/eventsnext.php?id={idTeam}
* **Previous match list**:
  * https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id={idLeague}
  * https://www.thesportsdb.com/api/v1/json/1/eventslast.php?id={idTeam}
* **Match details**: https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id={idEvent}
* **Match search**: https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e={query}
* **Team search**: https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t={query}
