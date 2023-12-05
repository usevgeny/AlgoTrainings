package LevelOne;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FindCelebrity {

    // Methods to prepare a dataset
    private List<RandomPerson> listOfPersons = new ArrayList<>();

    public void fillListOfPersons(List<RandomPerson> listToFill, int lengthOfList) {
        for (int i = 0; i < lengthOfList; i++) {
            listToFill.add(new RandomPerson(i));
        }
    }

    public RandomPerson getRandomPersonFromList(List<RandomPerson> listOfPersons) {
        Random ran = new Random();
        return listOfPersons.stream().filter(p -> p.getId() == ran.nextInt(listOfPersons.size())).findFirst()
                .orElse(null);
    }

    public void setKnowledge(int celebrityId) {
        RandomPerson celebrity = this.listOfPersons.stream().filter(p -> p.getId() == celebrityId).findFirst().orElse(null);

        this.listOfPersons.stream().filter(p -> p.getId() != celebrityId)
                .forEach(p -> {
                    p.addPersonToContacts(celebrity);
                    p.addPersonToContacts(getRandomPersonFromList(this.listOfPersons));
                    p.addPersonToContacts(getRandomPersonFromList(this.listOfPersons));
                    p.addPersonToContacts(getRandomPersonFromList(this.listOfPersons));
                });
    }

    // solution methods

    // 1. for each loop
    public RandomPerson forLoopFindCelebrity(List<RandomPerson> listOfPersons) {
        for (RandomPerson p : listOfPersons) {
            boolean isCelebrity = false;
            if (p.getPersonsHeKnows().isEmpty()) {
                isCelebrity = true;
            } else {
                continue;
            }
            for (RandomPerson person : listOfPersons) {
                if (!p.equals(person) && !person.getPersonsHeKnows().contains(p)) {
                    isCelebrity = false;
                    break;
                }
            }
            if (isCelebrity) {
                return p;
            }
        }
        return null;
    }
    public RandomPerson forLoopFindCelebrity(RandomPerson[] arrayOfPersons) {
        for (RandomPerson p : arrayOfPersons) {
            boolean isCelebrity = false;
            if (p.getPersonsHeKnows().isEmpty()) {
                isCelebrity = true;
            } else {
                continue;
            }
            for (RandomPerson person : arrayOfPersons) {
                if (!p.equals(person) && !person.getPersonsHeKnows().contains(p)) {
                    isCelebrity = false;
                    break;
                }
            }
            if (isCelebrity) {
                return p;
            }
        }
        return null;
    }
    
    // 2nd method: one step double check
    public RandomPerson findCelebrity(List<RandomPerson> listOfPersons) {
        int l = 0;
        int r = listOfPersons.size() - 1;
        while (l != r) {
            if (listOfPersons.get(l).knowsPerson(listOfPersons.get(r))) {
                l++;
            } else {
                r--;
            }
        }
        // check if everyone in the list knows the only one remaining candidate
        for (int i = 0; i < listOfPersons.size(); i++) {
            if (i != l && (!listOfPersons.get(i).knowsPerson(listOfPersons.get(l))
                    || listOfPersons.get(l).knowsPerson(listOfPersons.get(i)))) {
                return null;
            }
        }

        return listOfPersons.get(l);
    }
    public RandomPerson findCelebrity(RandomPerson[] listOfPersons) {
        int l = 0;
        int r = listOfPersons.length-1;
        while (l != r) {
            if (listOfPersons[l].knowsPerson(listOfPersons[r])) {
                l++;
            } else {
                r--;
            }
        }
        // check if everyone in the list knows the only one remaining candidate
        for (int i = 0; i < listOfPersons.length; i++) {
            if (i != l && (!listOfPersons[i].knowsPerson(listOfPersons[l])
                    || listOfPersons[l].knowsPerson(listOfPersons[i]))) {
                return null;
            }
        }

        return listOfPersons[l];
    }
    // 2nd method: one step double check
    public RandomPerson findCelebrityThirVariation(List<RandomPerson> listOfPersons) {
        int l = 0;
        int r = listOfPersons.size() - 1;
        while (l != r) {
            if (listOfPersons.get(l).getPersonsHeKnows().contains(listOfPersons.get(r))) {
                l++;
            } else {
                r--;
            }
        }
        // check if everyone in the list knows the only one remaining candidate
        for (int i = 0; i < listOfPersons.size(); i++) {
            if (i != l && (!listOfPersons.get(i).getPersonsHeKnows().contains(listOfPersons.get(l))
                    || listOfPersons.get(l).getPersonsHeKnows().contains(listOfPersons.get(i)))) {
                return null;
            }
        }

        return listOfPersons.get(l);
    }

    public static void main(String[] args) {

        // spreparing a dataset
        FindCelebrity findCelebrity = new FindCelebrity();
        findCelebrity.fillListOfPersons(findCelebrity.listOfPersons, 30000);
        findCelebrity.setKnowledge(2999);
        RandomPerson unknownPerson = new RandomPerson(99999999);
        unknownPerson.setPersonsHeKnows(findCelebrity.listOfPersons);
        findCelebrity.listOfPersons.add(unknownPerson);
        
        RandomPerson[] arrayOfPersons = findCelebrity.listOfPersons.toArray(new RandomPerson[0]);

        for (RandomPerson p : findCelebrity.listOfPersons) {
            System.out.println(p + " " + p.getPersonsHeKnows().toString());
        }

        // first method
        System.out.println();
        Long start = System.currentTimeMillis();
        System.out.println("Celebrity is : " + findCelebrity.forLoopFindCelebrity(findCelebrity.listOfPersons));
        Long end = System.currentTimeMillis();
        System.out.println("Time to execute FoorLoop based method: " + (end - start) + "millis");

        // first method
        System.out.println();
        start = System.currentTimeMillis();
        System.out.println("Celebrity is : " + findCelebrity.forLoopFindCelebrity(arrayOfPersons));
        end = System.currentTimeMillis();
        System.out.println("Time to execute FoorLoop based method with array in entry: " + (end - start) + "millis");
        // second method
        System.out.println();
        start = System.currentTimeMillis();
        System.out.println("Celebrity is : " + findCelebrity.findCelebrity(findCelebrity.listOfPersons));
        end = System.currentTimeMillis();
        System.out.println("Time to execute oneStep two checks based method: " + (end - start) + "millis");
        // second method
        System.out.println();
        start = System.currentTimeMillis();
        System.out.println("Celebrity is : " + findCelebrity.findCelebrity(arrayOfPersons));
        end = System.currentTimeMillis();
        System.out.println("Time to execute oneStep two checks based method using Array as an entry: " + (end - start) + "millis");
        // second method
        System.out.println();
        start = System.currentTimeMillis();
        System.out.println("Celebrity is : " + findCelebrity.findCelebrityThirVariation(findCelebrity.listOfPersons));
        end = System.currentTimeMillis();
        System.out.println("Time to execute oneStep two checks with a direct check: " + (end - start) + "millis");
    }

};

class RandomPerson {
    private int id;
    private List<RandomPerson> personsHeKnows = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<RandomPerson> getPersonsHeKnows() {
        return personsHeKnows;
    }

    public void setPersonsHeKnows(List<RandomPerson> personsHeKnows) {
        this.personsHeKnows = personsHeKnows;
    }

    public RandomPerson(int id) {
        super();
        this.id = id;
        this.personsHeKnows = new ArrayList<>(); // Initialize the list here
    }

    public void addPersonToContacts(RandomPerson newContact) {
        if (null != newContact && !this.getPersonsHeKnows().contains(newContact) && !this.equals(newContact)) {
            this.getPersonsHeKnows().add(newContact);
        }
    }

    public boolean knowsPerson(RandomPerson personToCheck) {
        return this.getPersonsHeKnows().contains(personToCheck);
    }

    @Override
    public String toString() {
        return "RandomPerson [id=" + id + "]";
    };

}