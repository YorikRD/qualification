package melnikov.qualification.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Party {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   @Column(nullable = false)
   private String title;
   @Column
   private String description;
   @ManyToOne(fetch =FetchType.LAZY)
   private Master master;
   @ManyToMany(mappedBy = "games")
   private Set<Player> players = new HashSet<>();

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

   public int getId() {
      return id;
   }
}
