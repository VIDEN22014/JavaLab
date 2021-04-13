import java.util.Random;

class Data {
    int key;
    int value;

    Data(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class Hashmap {
    Data[] mass;
    int size = 0;

    Hashmap() {
        mass = new Data[100];
    }

    Hashmap(int size) {
        mass = new Data[size];
    }

    void add(int key, int value) {
        if (addByMass(key, value, mass) == 1) {
            size++;
        }
        if (size >= mass.length / 2) {
            increaseLength();
        }
    }

    int addByMass(int key, int value, Data[] massive) {
        if (key >= 0) {
            int index = indexByKey(key, massive);//перевірка на те чи існує даний символ
            if (index == -1) {
                index = returnNearestEmpty(key, massive);
                massive[index] = new Data(key, value);
                return 1;
            } else {
                massive[index].key = key;
                massive[index].value = value;
            }
        }
        return -1;
    }

    void delete(int key) {
        if (key >= 0) {
            int index = indexByKey(key, mass);
            if (index != -1) {
                mass[index] = null;
                size--;
                return;
            }
        }
        System.out.println("Element by key is not exist");
    }

    int get(int key) {
        if (key >= 0) {
            int index = indexByKey(key, mass);
            if (index != -1) {
                return mass[index].value;
            }
        }
        throw new ArrayIndexOutOfBoundsException("Element by key is not exist");
    }

    void display() {
        for (Data i : mass) {
            if (i != null) {
                System.out.println(i.key + ": " + i.value);
            }
        }
    }

    void increaseLength() {
        Data[] tempMass = new Data[mass.length * 2];
        for (Data i : mass) {
            if (i != null) {
                addByMass(i.key, i.value, tempMass);
                i = null;
            }
        }
        mass = tempMass;
    }

    int hashFunc(int key, Data[] massive) {
        return (int) (key % 20 * (massive.length / 20.0));
    }

    int indexByKey(int key, Data[] massive) {
        int index = hashFunc(key, massive);
        int tempIndex = index;
        do {
            if (massive[tempIndex] != null) {
                if (massive[tempIndex].key == key) {
                    return tempIndex;
                }
            }
            tempIndex++;
            tempIndex = tempIndex % massive.length;
        } while (tempIndex != index);
        return -1;
    }

    int returnNearestEmpty(int key, Data[] massive) {
        int index = hashFunc(key, massive);
        int tempIndex = index;
        do {
            if (massive[tempIndex] == null) {
                return tempIndex;
            }
            tempIndex = (tempIndex + 1) % massive.length;
        } while (tempIndex != index);
        return -1;
    }
}

public class Lab1 {
    public static void main(String[] args) {
        Hashmap hashmap = new Hashmap(2);
        for (int i = 0; i < 10; i++) {
            hashmap.add(i, i * 2);
        }
        hashmap.delete(0);
        hashmap.display();
        System.out.println(hashmap.get(8));
    }
}
