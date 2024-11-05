package PCU_RPG_GAME.ViktorImpl;

import java.util.HashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        HashMap<String, IProperty> knightProperties = new HashMap<>();
        knightProperties.put("strength", new Property("strentgh", 5));
        knightProperties.put("dexterity", new Property("dexterity", 2));
        knightProperties.put("magic", new Property("magic", 2));

        HashMap<String, IAttack> knightAttacks = new HashMap<>();
        knightAttacks.put("melee", new MeleeAttack("melee", knightProperties));
        knightAttacks.put("ranged", new RangedAttack("ranged", knightProperties));
        knightAttacks.put("spell", new SpellAttack("spell", knightProperties));

        Hero knight = new Hero("knight", knightProperties, knightAttacks);

        IAmulet amulet1 = new Amulet("Amulet of Strength");
        amulet1.addPropertyDecorator(new PropertyDecorator("strength", 2));
        amulet1.addPropertyDecorator(new PropertyDecorator("dexterity", -1));

        IAmulet amulet2 = new Amulet("Amulet of Harry Potter");
        amulet2.addAttackDecorator(new AttackDecorator("spell", 4));
        amulet2.addAttackDecorator(new AttackDecorator("ranged", -2));
        amulet2.addAttackDecorator(new AttackDecorator("melee", -2));

        IAmulet amulet3 = new Amulet("Amulet of Legolas");
        amulet3.addPropertyDecorator(new PropertyDecorator("dexterity", 2));
        amulet3.addAttackDecorator(new AttackDecorator("ranged", 3));
        amulet3.addPropertyDecorator(new PropertyDecorator("strength", -1));

        knight.printInfo();

        knight.attachAmulet(amulet1);

        knight.printInfo();

        knight.attachAmulet(amulet2);

        knight.printInfo();

        knight.attachAmulet(amulet3);

        knight.printInfo();
    }
}

interface IHero {
    String getType();
    double calculateAttack(String type);
    double calculateProperty(String type);
    void attachAmulet(IAmulet amulet);

    void printInfo();
}

class Hero implements IHero {
    private String type;
    private HashMap<String, IProperty> properties;
    private HashMap<String, IAttack> attacks;
    private LinkedList<IAmulet> amulets = new LinkedList<>();

    public Hero(String type, HashMap<String, IProperty> properties, HashMap<String, IAttack> attacks) {
        this.type = type;
        this.properties = properties;
        this.attacks = attacks;
    }


    @Override
    public String getType() {
        return type;
    }

    @Override
    public double calculateAttack(String type) {
        return attacks.get(type).getValue();
    }

    @Override
    public double calculateProperty(String type) {
        return attacks.get(type).getValue();
    }

    @Override
    public void attachAmulet(IAmulet amulet) {
        System.out.printf("Attaching amulet: %s\n", amulet.getName());
        amulets.add(amulet);
        for(var propertyDecorator: amulet.getPropertyDecorators()){
            IProperty property = properties.get(propertyDecorator.getType());
            properties.put(propertyDecorator.getType(), propertyDecorator.decorate(property));
        }
        for(var attackDecorator: amulet.getAttackDecorators()){
            IAttack attack = attacks.get(attackDecorator.getType());
            attacks.put(attackDecorator.getType(), attackDecorator.decorate(attack));
        }
    }

    @Override
    public void printInfo() {
        System.out.printf("Type of hero: %s\n", getType());
        System.out.printf("Properties:\n\tStrength: %.1f\n\tDexterity: %.1f\n\tMagic: %.1f\n",
                this.calculateProperty("strength"),
                this.calculateProperty("dexterity"),
                this.calculateProperty("magic"));
        System.out.printf("Attacks:\n\tMelee: %.1f\n\tRanged: %.1f\n\tSpell: %.1f\n",
                this.calculateAttack("melee"),
                this.calculateAttack("ranged"),
                this.calculateAttack("spell"));
    }
}

interface IProperty {
    String getType();
    double getValue();
}

interface IAttack {
    String getType();
    double getValue();
}

class Property implements IProperty {

    private String type;
    private double value;

    public Property(String type, double value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public double getValue() {
        return this.value;
    }
}

abstract class Attack implements IAttack {

    private String type;
    private HashMap<String, IProperty> properties;

    public Attack(String type, HashMap<String, IProperty> properties) {
        this.type = type;
        this.properties = properties;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public HashMap<String, IProperty> getProperties() {
        return properties;
    }
}

class MeleeAttack extends Attack{

    public MeleeAttack(String type, HashMap<String, IProperty> properties) {
        super(type, properties);
    }

    @Override
    public double getValue() {
        return getProperties().get("strength").getValue() * getProperties().get("dexterity").getValue();
    }
}

class RangedAttack extends Attack{

    public RangedAttack(String type, HashMap<String, IProperty> properties) {
        super(type, properties);
    }

    @Override
    public double getValue() {
        return getProperties().get("magic").getValue() * getProperties().get("dexterity").getValue();
    }
}

class SpellAttack extends Attack{

    public SpellAttack(String type, HashMap<String, IProperty> properties) {
        super(type, properties);
    }

    @Override
    public double getValue() {
        return getProperties().get("strength").getValue() * getProperties().get("magic").getValue();
    }
}

abstract class APropertyDecorator implements IProperty {
    private String type;
    private IProperty property;

    public APropertyDecorator(String type) {
        this.type = type;
    }

    IProperty decorate(IProperty property){
        this.property = property;
        return this;
    }
    public String getType(){
        return type;
    }

    public IProperty getDecorated(){
        return property;
    }
}

class PropertyDecorator extends APropertyDecorator {

    private double modificator;

    public PropertyDecorator(String type, double modificator) {
        super(type);
        this.modificator = modificator;
    }


    @Override
    public double getValue() {
        return getDecorated().getValue() + modificator;
    }
}

abstract class AAttackDecorator implements IAttack {
    private String type;
    private IAttack attack;

    public AAttackDecorator(String type) {
        this.type = type;
    }

    IAttack decorate(IAttack attack){
        this.attack = attack;
        return this;
    }
    public String getType(){
        return type;
    }

    public IAttack getDecorated(){
        return attack;
    }
}

class AttackDecorator extends AAttackDecorator {

    private double modificator;
    public AttackDecorator(String type, double modificator) {
        super(type);
        this.modificator = modificator;
    }

    @Override
    public double getValue() {
        return getDecorated().getValue() + modificator;
    }
}

interface IAmulet {
    void addPropertyDecorator(APropertyDecorator decorator);
    void addAttackDecorator(AAttackDecorator decorator);
    LinkedList<APropertyDecorator> getPropertyDecorators();
    LinkedList<AAttackDecorator> getAttackDecorators();
    String getName();
}

class Amulet implements IAmulet {

    private LinkedList<AAttackDecorator> attackDecorators = new LinkedList<>();
    private LinkedList<APropertyDecorator> propertyDecorators = new LinkedList<>();
    private String name;

    public Amulet(String name) {
        this.name = name;
    }

    @Override
    public void addPropertyDecorator(APropertyDecorator decorator) {
        propertyDecorators.add(decorator);
    }

    @Override
    public void addAttackDecorator(AAttackDecorator decorator) {
        attackDecorators.add(decorator);
    }

    @Override
    public LinkedList<APropertyDecorator> getPropertyDecorators() {
        return propertyDecorators;
    }

    @Override
    public LinkedList<AAttackDecorator> getAttackDecorators() {
        return attackDecorators;
    }

    @Override
    public String getName() {
        return name;
    }
}