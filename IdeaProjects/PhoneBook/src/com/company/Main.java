package com.company;

public class Main {

    public static void main(String[] args) {

        PhoneBook MyPhoneBook = new PhoneBook();

        MyPhoneBook.createNewEntry("John", "Doe", "123@123.com", "5555555");
        MyPhoneBook.createNewEntry("Jane", "Doe", "321@123.com", "5555555");
        MyPhoneBook.DisplayPhoneBook();

        MyPhoneBook.DisplayEntry(MyPhoneBook.getEntryByKey("Jane Doe"));

        MyPhoneBook.DeleteEntry("John Doe");
        MyPhoneBook.DisplayPhoneBook();

    }
}
