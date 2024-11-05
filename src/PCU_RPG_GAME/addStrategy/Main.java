//package PCU_RPG_GAME.addStrategy;
//
//public class Main {
//    public static void main(String[] args) {
//
//        IHero knight = new Knight();
//        knight = new StrengthAmulet(new HarryPotterAmulet(new LegolasAmulet(knight)));
//
//        IHero archer = new Archer();
//        archer = new StrengthAmulet(new HarryPotterAmulet(archer));
//
//        IHero sorcerer = new Sorcerer();
//        sorcerer = new LegolasAmulet(sorcerer);
//
//        System.out.println("Knight Melee: " + knight.meleeAttack());
//        System.out.println("Knight Ranged: " + knight.rangedAttack());
//        System.out.println("Knight Spell: " + knight.spellAttack());
//
//        System.out.println("Archer Melee: " + archer.meleeAttack());
//        System.out.println("Archer Ranged: " + archer.rangedAttack());
//        System.out.println("Archer Spell: " + archer.spellAttack());
//
//        System.out.println("Sorcerer Melee: " + sorcerer.meleeAttack());
//        System.out.println("Sorcerer Ranged: " + sorcerer.rangedAttack());
//        System.out.println("Sorcerer Spell: " + sorcerer.spellAttack());
//    }
//}
//
//class AttributesOfHero {
//    private int strength;
//    private int dexterity;
//    private int magic;
//
//
//
//    public int getStrength() {
//        return strength;
//    }
//
//    public int getDexterity() {
//        return dexterity;
//    }
//
//    public int getMagic() {
//        return magic;
//    }
//}
//
//class meleeAttack extends AttributesOfHero{
//    public int meleeAttackAction() {
//        return getStrength() * getDexterity();
//    }
//}
//class rangedAttack{
//    public int rangedAttackAction() {
//        return getDexterity() * getMagic();
//    }
//}
//class spellAttack{
//    public int spellAttackAction() {
//        return getMagic() * getStrength();
//    }
//}
//interface IHero {
//    int getStrength();
//    int getDexterity();
//    int getMagic();
//}
//
//
//abstract class Hero implements IHero {
//
//    @Override
//    public int getStrength() {
//        return strength;
//    }
//
//    @Override
//    public int getDexterity() {
//        return dexterity;
//    }
//
//    @Override
//    public int getMagic() {
//        return magic;
//    }
//}
//
//
//class Knight extends Hero {
//    public Knight() {
//        super(5, 2, 2);
//    }
//}
//
//class Archer extends Hero {
//    public Archer() {
//        super(2, 5, 2);
//    }
//}
//
//class Sorcerer extends Hero {
//    public Sorcerer() {
//        super(2, 2, 5);
//    }
//}
//
//abstract class HeroDecorator implements IHero {
//    protected IHero hero;
//
//    public HeroDecorator(IHero hero) {
//        this.hero = hero;
//    }
//
//    @Override
//    public int meleeAttack() {
//        return hero.meleeAttack();
//    }
//
//    @Override
//    public int rangedAttack() {
//        return hero.rangedAttack();
//    }
//
//    @Override
//    public int spellAttack() {
//        return hero.spellAttack();
//    }
//
//    @Override
//    public int getStrength() {
//        return hero.getStrength();
//    }
//
//    @Override
//    public int getDexterity() {
//        return hero.getDexterity();
//    }
//
//    @Override
//    public int getMagic() {
//        return hero.getMagic();
//    }
//}
//
//class StrengthAmulet extends HeroDecorator {
//
//    public StrengthAmulet(IHero hero) {
//        super(hero);
//    }
//    public int getStrength() {
//        return hero.getStrength() + 2;
//    }
//    public int getDexterity() {
//        return hero.getDexterity() - 1;
//    }
//}
//
//class HarryPotterAmulet extends HeroDecorator {
//    public HarryPotterAmulet(IHero hero) {
//        super(hero);
//    }
//    public int spellAttack() {
//        return hero.spellAttack() + 4;
//    }
//    public int meleeAttack() {
//        return hero.meleeAttack() - 2;
//    }
//    public int rangedAttack() {
//        return hero.rangedAttack() - 2;
//    }
//}
//
//class LegolasAmulet extends HeroDecorator {
//    public LegolasAmulet(IHero hero){
//        super(hero);
//    }
//    public int getDexterity() {
//        return hero.getDexterity() + 2;
//    }
//    public int rangedAttack() {
//        return hero.rangedAttack() + 3;
//    }
//    public int getStrength() {
//        return 1;
//    }
//}
//
