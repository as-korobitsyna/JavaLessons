package com.company;

public class Main {

    public static void main(String[] args) {

        PhoneBook MyPhoneBook = new PhoneBook();

        //Creating new entries
        System.out.println("Creating two new entries");
        MyPhoneBook.createNewEntry("John", "Doe", "123@123.com", "5555555");
        MyPhoneBook.createNewEntry("Jane", "Doe", "321@123.com", "5555555");
        MyPhoneBook.DisplayPhoneBook();

        //Finding entry
        System.out.println("Finding and displaying entry");
        MyPhoneBook.DisplayEntry(MyPhoneBook.getEntryByKey("Jane Doe"));

        //Deleting
        System.out.println("Deleting entry and displaying new phonebook");
        MyPhoneBook.DeleteEntry("John Doe");
        MyPhoneBook.DisplayPhoneBook();

    }
}
