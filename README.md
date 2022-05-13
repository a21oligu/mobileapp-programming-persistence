
# Rapport

Uppgiften gick ut på att skapa en SQLite databas som man med appen kan skriva och läsa ifrån. I denna app kan man med appen skapa godisar. Man kan även hämta och lista upp de olika godisarna i en SecondActivity.

För att underlätta och minska fel genom appen skapades klasserna ***___DatabaseHelper___*** och ***___DatabaseTables___***. 

Klassen *DatabaseHelper* extendar SQLiteOpenHelper. Denna klass hjälper till att skapa, öppna och hålla koll på egenskaper hos databasen såsom t.ex version.

Klassen *DatabaseTables* består av statiska fält, eller konstanter, som används för att skapa och ta bort databasen. Denna har även en nodell för hur en godis ska se ut i databasen. Denna modell är i from av en innre klass med namn Candy, se kodblock nedan:

```Java
public class Candy {
    static final String COLUMN_NAME = "name"; // primary key
    static final String COLUMN_TASTE = "taste";
    static final String COLUMN_COLOR = "color";
    static final String TABLE_NAME = "Candy";
}
```

Denna används sedan i konstanten *SQL_CREATE_TABLE_CANDY* som finns i *DatabaseTables*, se kodblock nedan:

```Java
public static final String SQL_CREATE_TABLE_CANDY = String.format(
        "CREATE TABLE %s (%s TEXT PRIMARY KEY, %s TEXT, %s TEXT)",
        Candy.TABLE_NAME,
        Candy.COLUMN_NAME,
        Candy.COLUMN_TASTE,
        Candy.COLUMN_COLOR);
```

För att sedan initiera databasen deklarerades följande kod i MainActivity#onCreate(): 

```Java
this.databaseHelper = new DatabaseHelper(this);
this.database = this.databaseHelper.getWritableDatabase();
```

<img src="app_initial.png" height="350px" >
<img src="app_edit.png" height="350px" >
<img src="app_read.png" height="350px" >
