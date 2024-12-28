package dev.lpa;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;

class Player implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final int version = 2;
    private String name;
    private long topScore;
    private long bigScore;
    private final transient long accountId;
    private List<String> collectedWeapons = new LinkedList<>();

    public Player(long accountId, String name, int topScore, List<String> collectedWeapons) {
        this.accountId = accountId;
        this.name = name;
        this.topScore = topScore;
        this.collectedWeapons = collectedWeapons;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + accountId + ", " +
                "name='" + name + '\'' +
                ", topScore=" + topScore +
                ", collectedWeapons=" + collectedWeapons +
                '}';
    }

    @Serial
    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException
    {
        var serializedVer = stream.readInt();
        collectedWeapons = (List<String>) stream.readObject();
        name = stream.readUTF();
        topScore = (serializedVer == 1) ? stream.readInt() : stream.readLong();
    }

    @Serial
    private void writeObject(ObjectOutputStream stream)
            throws IOException
    {
        out.println("--> Customized Writing");
        stream.writeInt(version);
        stream.writeObject(collectedWeapons);
        stream.writeUTF(name);
        stream.writeLong(topScore);
    }
}

public class Main {

    public static void main(String[] args) {
        Player tim = new Player(555, "Tim", 100_000_010,
                List.of("knife", "machete", "pistol"));
        out.println(tim);

        Path timFile = Path.of("tim.dat");
        Player reconstitutedTim = readObject(timFile);
        out.println(reconstitutedTim);

        Player joe = new Player(556, "Joe", 75,
                List.of("crossbow", "rifle", "pistol"));
        Path joeFile = Path.of("joe.dat");
        writeObject(joeFile, joe);
        Player reconstitutedJoe = readObject(joeFile);
        out.println(joe);
        out.println(reconstitutedJoe);
    }

    private static void writeData(Path dataFile) {
        try (DataOutputStream dataStream = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(dataFile.toFile())));)
        {
            int myInt = 17;
            long myLong = 100_000_000_000_000L;
            boolean myBoolean = true;
            char myChar = 'Z';
            float myFloat = 77.7f;
            double myDouble = 98.6;
            String myString = "Hello World";

            long position = 0;
            dataStream.writeInt(myInt);
            out.println("writeInt writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeLong(myLong);
            out.println("writeLong writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeBoolean(myBoolean);
            out.println("writeBoolean writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeChar(myChar);
            out.println("writeChar writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeFloat(myFloat);
            out.println("writeFloat writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeDouble(myDouble);
            out.println("writeDouble writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeUTF(myString);
            out.println("writeUTF writes " + (dataStream.size() - position));
            position = dataStream.size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readData(Path dataFile) {
        try (DataInputStream dataStream = new DataInputStream(Files.newInputStream(dataFile)))
        {
            out.println("myInt = " + dataStream.readInt());
            out.println("myLong = " + dataStream.readLong());
            out.println("myBoolean = " + dataStream.readBoolean());
            out.println("myChar = " + dataStream.readChar());
            out.println("myFloat = " + dataStream.readFloat());
            out.println("myDouble = " + dataStream.readDouble());
            out.println("myString = " + dataStream.readUTF());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeObject(Path dataFile, Player player) {
        try (ObjectOutputStream objStream =
                 new ObjectOutputStream(Files.newOutputStream(dataFile)))
        {
            objStream.writeObject(player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Player readObject(Path dataFile) {
        try (ObjectInputStream objStream =
                     new ObjectInputStream(Files.newInputStream(dataFile)))
        {
            return (Player) objStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
