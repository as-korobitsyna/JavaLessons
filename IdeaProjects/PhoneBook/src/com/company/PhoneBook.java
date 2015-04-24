//TODO: store phonebook somewhere (XML?)

package com.company;

import java.util.*;

public class PhoneBook {

    TreeMap phonebook;

    public PhoneBook()
    {
        phonebook = new TreeMap();
    }

    //TODO: Check data for valid values
    public PhoneBookEntry createNewEntry(String name, String surname, String email, String phonenumber)
    {
        PhoneBookEntry newEntry = new PhoneBookEntry(name, surname, email, phonenumber);
        phonebook.put(name + " " + surname, newEntry);
        return newEntry;
    }

    //TODO: errors "entry not found"
    public PhoneBookEntry getEntryByKey(String key)
    {
        PhoneBookEntry foundEntry;
        foundEntry = (PhoneBookEntry)phonebook.get(key);
        return foundEntry;
    }

    //TODO: can not delete from empty Phonebook, can not delete if key not found
    public void DeleteEntry(String key)
    {
        phonebook.remove(key);
    }

    //TODO: implement this
    public void EditEntry(String key, String newName, String newSurname, String newEmail, String newPhonenumber)
    {
    }


    public void DisplayEntry(PhoneBookEntry entry)
    {
        System.out.println("Name: " + entry.name);
        System.out.println("Surname: " + entry.surname);
        System.out.println("Phone number: " + entry.phonenumber);
        System.out.println("e-mail: " + entry.email + "\n");
    }

    public void DisplayPhoneBook()
    {
        PhoneBookEntry DisplayedEntry;

        System.out.println("\n\nPhonebook:");

        Set set = phonebook.entrySet();
        for (Object aSet : set) {
            Map.Entry me = (Map.Entry) aSet;
            DisplayedEntry = (PhoneBookEntry) me.getValue();
            DisplayEntry(DisplayedEntry);
        }
    }

}