import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import java.awt.Graphics;

  public class Controller  {
      protected LinkedList<EntityA> ea = new LinkedList<EntityA>();
      private LinkedList<EntityB> eb = new LinkedList<EntityB>();
      EntityA entA;
      EntityB entB;
      private Textures tex;
      private Galga game;
      Random r = new Random();

      public void createEnemy(int enemy_count) {
          for(int i=0; i < enemy_count; i++) {

              addEntity(new Enemy(r.nextInt(640), -10, tex,this,game)); //generating spaceships


          }


      }

      public void tick () {
          // A CLASS

          for (int i = 0; i < ea.size(); i++) {
              entA = ea.get(i);

              entA.tick();

          }

          // B CLASS

          for (int i = 0; i < eb.size(); i++) {
              entB = eb.get(i);

              entB.tick();
          }

      }

          public void render (Graphics g){
          // A CLASS
              for (int i = 0; i < ea.size(); i++) {
                  entA = ea.get(i);

                  entA.render(g);

              }
              // B CLASS
              for (int i = 0; i < eb.size(); i++) {
                  entB = eb.get(i);

                  entB.render(g);

              }
          }

      public Controller(Textures tex, Galga game) {
          this.tex = tex;
          this.game=game;



      }
      public void addEntity (EntityA block) {
          ea.add(block);
      }
      public void removeEntity (EntityA block) {
          ea.remove(block);
      }

      public void addEntity (EntityB block) {
          eb.add(block);
      }
      public void removeEntity (EntityB block) {
          eb.remove(block);
      }
      public void removeAllEntity (LinkedList<EntityB> eb) {
          eb.clear();
      }
      public void removeAllBullets (LinkedList<EntityA> ea) {
          ea.clear();
      }
      public  LinkedList<EntityA> getEntityA() {
          return ea;
      }
      public  LinkedList<EntityB> getEntityB() {
          return eb;
      }
  }




