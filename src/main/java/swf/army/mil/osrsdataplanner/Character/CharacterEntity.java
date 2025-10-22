package swf.army.mil.osrsdataplanner.Character;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class CharacterEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String username;
        private double combatLevel;
        private double totalXP;
        private Timestamp requestCreated;

        public Long getId() {return id;}
        public String getUsername() {return username;}
        public double getCombatLevel() {return combatLevel;}
        public double getTotalXP() {return totalXP;}
        public Timestamp getRequestCreated() {return requestCreated;}


        public void setId(Long id) {this.id = id;}
        public void setUsername(String username) {this.username = username;}
        public void setCombatLevel(double combatLevel) {this.combatLevel = combatLevel;}
        public void setTotalXP(double totalXP) {this.totalXP = totalXP;}
        public void setRequestCreated(Timestamp requestCreated) {this.requestCreated = requestCreated;}

        public CharacterEntity() {}

        public CharacterEntity(Long id, String username, double combatLevel, double totalXP, Timestamp requestCreated) {
            this.id = id;
            this.username = username;
            this.combatLevel = combatLevel;
            this.totalXP = totalXP;
            this.requestCreated = requestCreated;
        }

    }
