package d00.ex05;

import java.util.Scanner;

public class Program {

    private static final int MAX_NUM_STUDENTS = 10;
    private static final int NUM_LESSONS_WEEK = 10;
    private static final int MAX_NAME_LENGTH = 10;
    private static final int MAX_NUM_HOURS = 5;
    private static final int NUM_DAYS_WEEK = 7;
    private static final int MAX_NUM_ATTEMPTS = 3;
    private static final int NUM_LESSONS_MONTH = 50;

    private static int countLessons = 0;
    private static int countAttempts = 0;

    private static final int[] arrDates = new int[NUM_LESSONS_WEEK];
    private static final int[] arrTime = new int[NUM_LESSONS_WEEK];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] students = new String[MAX_NUM_STUDENTS];
        createListStudents(scanner, students);

        int[] studyTime = new int[NUM_LESSONS_WEEK];
        String[] studyDay = new String[NUM_LESSONS_WEEK];
        createListLessons(scanner, studyTime, studyDay);

        sortLessonsDays(studyTime, studyDay);

        String[][] scheduleTable = new String[NUM_LESSONS_WEEK + 1][NUM_LESSONS_WEEK + 1];

        fillScheduleTable(scheduleTable, studyDay, studyTime);

        fillAttendanceTable(scanner, students, scheduleTable);

        printResultTable(students, scheduleTable);
    }

    private static void createListStudents(Scanner scanner, String[] students){
        String name;
        int countAttempts = 0;

        for (int i = 0; i <= MAX_NUM_STUDENTS; i++) {
            name = scanner.nextLine();
            if (i == MAX_NUM_STUDENTS && !name.equals(".")) {
                System.err.println("Error: The maximum number of students is 10");
                break;
            }else if (name.equals(".")){
                break;
            }else if (name.length() == 0 || name.length() > MAX_NAME_LENGTH){
                countAttempts++;
                i = getCheckStatusError(i, "name length", countAttempts, MAX_NUM_STUDENTS);
            } else{
                students[i] = name;
            }
        }
    }

    private static void createListLessons(Scanner scanner, int[] studyTime, String[] studyDay){
        String day;
        int time;
        int checkNumTime, checkNumDay;

        for (int i = 0; i <= NUM_LESSONS_WEEK; i++) {
            if (scanner.hasNextInt() && i != NUM_LESSONS_WEEK) {
                time = scanner.nextInt();
                checkNumTime = checkValidateTime(time, studyTime);
                if (checkNumTime == -1) {
                    break;
                } else if (checkNumTime == 0) {
                    day = scanner.next();
                    checkNumDay = checkValidateDay(day, studyDay);
                    if (checkNumDay == -1) {
                        break;
                    } else if (checkNumDay == 1) {
                        i--;
                    } else {
                        countLessons++;
                    }
                } else {
                    i--;
                }
            }else if (scanner.hasNextInt() && i == NUM_LESSONS_WEEK){
                System.err.println("Error: Maximum number of lessons = 10");
                break;
            }else{
                day = scanner.next();

                if (day.equals(".")){
                    break;
                }else{
                    checkNumDay = checkValidateDay(day, studyDay);
                    if (checkNumDay == -1){
                        break;
                    }else if (checkNumDay == 0){
                        time = scanner.nextInt();
                        checkNumTime = checkValidateTime(time, studyTime);
                        if (checkNumTime == -1){
                            break;
                        }else if (checkNumTime == 1){
                            i--;
                        }else{
                            countLessons++;
                        }
                    }else{
                        i--;
                    }
                }
            }
        }
    }

    private static int checkValidateTime(int time, int[] studyTime) {
        int checkNum = 0;

        if (time >= 1 && time < 6) {
            studyTime[countLessons] = time;
        } else {
            countAttempts++;
            if (countAttempts == MAX_NUM_ATTEMPTS) {
                System.err.println("Error: Classes are held from 1:00pm to 6:00pm. Number of attempts exceeded");
                checkNum = -1;
            } else {
                System.out.println("Warning: Classes are held from 1:00pm to 6:00pm. Enter a valid lesson time");
                System.out.println("Attempts left: " + (MAX_NUM_ATTEMPTS - countAttempts));
                checkNum = 1;
            }
        }
        return checkNum;
    }

    private static int checkValidateDay(String day, String[] studyDay){
        int checkNum = 0;

        int idxDayWeek = getIdxDayWeek(day);

        if (idxDayWeek != -1) {
            studyDay[countLessons] = day;
        } else {
            countAttempts++;
            if (countAttempts == MAX_NUM_ATTEMPTS) {
                System.err.println("Error: Day of the week not found. Number of attempts exceeded");
                checkNum = -1;
            } else {
                System.out.println("Warning: Day of the week not found. Enter the correct time and day of the week");
                System.out.println("Attempts left: " + (MAX_NUM_ATTEMPTS - countAttempts));
                checkNum = 1;
            }
        }
        return checkNum;
    }

    private static int getIdxDayWeek(String day){
        String[] days = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};

        for (int i = 0; i < NUM_DAYS_WEEK; i++) {
            if (days[i].equals(day))
                return i;
        }
        return -1;
    }

    private static void sortLessonsDays(int[] studyTime, String[] studyDay){
        int idxDay1, idxDay2, time1, time2, buffTime;
        String buffDay;

        for (int i = 0; i < countLessons - 1; i++) {
            for (int j = 0; j < countLessons - i - 1; j++) {
                idxDay1 = getIdxDayWeek(studyDay[j]);
                idxDay2 = getIdxDayWeek(studyDay[j + 1]);
                time1 = studyTime[j];
                time2 = studyTime[j + 1];

                if (idxDay1 > idxDay2 || (idxDay1 == idxDay2 && time1 > time2)){
                    buffDay = studyDay[j];
                    buffTime = studyTime[j];
                    studyDay[j] = studyDay[j + 1];
                    studyTime[j] = studyTime[j + 1];
                    studyDay[j + 1] = buffDay;
                    studyTime[j + 1] = buffTime;
                }
            }
        }
    }

    private static void fillScheduleTable(String[][] scheduleTable, String[] studyDay, int[] studyTime){
        int date;
        int count = 0;

        for (int i = 0; i < MAX_NUM_HOURS; i++) {
            for (int j = 0; j < countLessons; j++) {
                if (count == 10){
                    break;
                }
                date = (i * NUM_DAYS_WEEK) + getIdxDayWeek(studyDay[j]);
                if (date != 0 && date < 31){
                    arrDates[count] = date;
                    arrTime[count] = studyTime[j];
                    scheduleTable[0][count] = studyTime[j] + ":00 " + studyDay[j] + " " + date;
                    count++;
                }
            }
        }

    }

    private static int getIdxNameStudent(String[] students, String name){
        for (int i = 0; i < MAX_NUM_STUDENTS; i++) {
            if (name.equals(students[i])){
                return i;
            }
        }
        return -1;
    }

    private static int getIdxTimeLesson(int date, int time){
        for (int i = 0; i < NUM_LESSONS_WEEK; i++) {
            if (date == arrDates[i] && time == arrTime[i]){
                return i;
            }
        }
        return -1;
    }

    private static int getCheckStatusError(int idx, String nameError, int countAttempts, int codeExit){
        if (countAttempts == MAX_NUM_ATTEMPTS) {
            System.err.println("Error: Invalid " + nameError + ". Number of attempts exceeded");
            idx = codeExit;
        } else {
            System.out.println("Warning: Invalid " + nameError + ". Enter a valid " + nameError);
            System.out.println("Attempts left: " + (MAX_NUM_ATTEMPTS - countAttempts));
            idx--;
        }
        return idx;
    }

    private static void fillAttendanceTable(Scanner scanner, String[] students, String[][] scheduleTable){
        int countAttempts = 0;

        String name, status;
        int date, time, idxStudent, idxTimeLesson;

        for (int i = 0; i < NUM_LESSONS_MONTH; i++) {
            if (scanner.hasNext()) {
                name = scanner.next();
                if (name.equals(".")){
                    break;
                }
                if (scanner.hasNextInt()) {
                    time = scanner.nextInt();
                    if (scanner.hasNextInt()) {
                        date = scanner.nextInt();
                        if (scanner.hasNext()) {
                            status = scanner.next();

                            idxStudent = getIdxNameStudent(students, name);
                            if (idxStudent == -1) {
                                countAttempts++;
                                i = getCheckStatusError(i, "name", countAttempts, NUM_LESSONS_MONTH);
                            } else {
                                idxTimeLesson = getIdxTimeLesson(date, time);
                                if (idxTimeLesson != -1) {
                                    if (status.equals("HERE")) {
                                        scheduleTable[idxStudent + 1][idxTimeLesson] = "1";
                                    } else if (status.equals("NOT_HERE")) {
                                        scheduleTable[idxStudent + 1][idxTimeLesson] = "-1";
                                    } else {
                                        countAttempts++;
                                        i = getCheckStatusError(i, "status", countAttempts, NUM_LESSONS_MONTH);
                                    }
                                }
                            }
                        } else {
                            countAttempts++;
                            i = getCheckStatusError(i, "status", countAttempts, NUM_LESSONS_MONTH);
                        }
                    } else {
                        countAttempts++;
                        i = getCheckStatusError(i, "date", countAttempts, NUM_LESSONS_MONTH);
                    }
                } else {
                    countAttempts++;
                    i = getCheckStatusError(i, "time", countAttempts, NUM_LESSONS_MONTH);
                }
            } else {
                countAttempts++;
                i = getCheckStatusError(i, "name", countAttempts, NUM_LESSONS_MONTH);
            }
        }
    }

    private static void printResultTable(String[] students, String[][] scheduleTable){
        int len;

        for (int i = 0; i < NUM_LESSONS_WEEK; ++i) {
            if (i == 0){
                for (int j = 0; j < NUM_LESSONS_WEEK; j++) {
                    System.out.print(" ");
                }
            }else {
                if (students[i - 1] == null) {
                    i++;
                    continue;
                }
                len = students[i - 1].length();
                for (int j = len; MAX_NAME_LENGTH - j != 0; j++) {
                    System.out.print(" ");
                }
                System.out.print(students[i - 1]);
            }
            for (int j = 0; j < NUM_LESSONS_WEEK; ++j) {
                if (scheduleTable[i][j] != null) {
                    len = scheduleTable[i][j].length();
                    for (int k = len; scheduleTable[0][j].length() - k != 0 ; k++) {
                        System.out.print(" ");
                    }
                    System.out.print(scheduleTable[i][j]);
                    System.out.print("|");
                }
                else {
                    if (scheduleTable[0][j] == null) {
                        len = 0;
                    }
                    else {
                        len = scheduleTable[0][j].length();
                    }
                    for (int k = 0; k < len; k++) {
                        System.out.print(" ");
                    }
                    if (len != 0) {
                        System.out.print("|");
                    }
                }
            }
            System.out.println();
        }
    }
}