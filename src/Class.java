import Enums.AbilityScore;
import Enums.Skill;
import Enums.WeaponType;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Class {
    public String name;
    public String description;

    public int level;
    public int hitDie;
    public int hitStarting;
    public Callable<Boolean> muticlassPrerequisites;
    public List<AbilityScore> savingThrowProficiencies = new ArrayList<>();
    public List<Skill> skillProficiencies = new ArrayList<>();
    public List<Enums.ArmorType> armorProficiencies = new ArrayList<>();
    public List<WeaponType> weaponTypeProficiencies = new ArrayList<>();
    public List<Weapon> individualWeaponProficiencies = new ArrayList<>();
    public List<Tool> toolProficiencies = new ArrayList<>();
    public List<Tool> multiclassingToolProficiencies = new ArrayList<>();
    public List<Enums.ArmorType> muticlassingArmorProficiencies = new ArrayList<>();
    public List<WeaponType> mutliclassingWeaponTypeProficiencies = new ArrayList<>();
    public List<Weapon> multiclassingIndividualWeaponProficiencies = new ArrayList<>();
    public String startingEquipmentText;
    public List<String> languages = new ArrayList<>();

    public List<Feature> classFeatures = new ArrayList<>();
    public List<Feature> optionalClassFeatures = new ArrayList<>();
    public List<HashMap<String, Integer>> limitedResources; //TODO: figure out how to add to main pc in constructor rather than here.

}
