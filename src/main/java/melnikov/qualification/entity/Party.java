package melnikov.qualification.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Integer.class
)
public class Party extends Identifier {
   @Column(nullable = false)
   private String title;
   @Column
   private String description;
   @ManyToOne(fetch =FetchType.LAZY)
   private Master master;
   @ManyToMany(mappedBy = "games")
   private Set<Player> players = new HashSet<>();
   @OneToMany(mappedBy = "game")
   private Set<Interval> meetings = new HashSet<>();

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Master getMaster() {
      return master;
   }

   public void setMaster(Master master) {
      this.master = master;
   }

   public Set<Player> getPlayers() {
      return players;
   }

   public void setPlayers(Set<Player> players) {
      this.players = players;
   }

   public Set<Interval> getMeetings() {
      return meetings;
   }

   public void setMeetings(Set<Interval> meetings) {
      this.meetings = meetings;
   }
}
