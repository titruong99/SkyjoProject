package g54900.skyjo.jdbc.dto;

/**
 *
 * @author timmy
 */
public class PlayerSkyjoDto {

    private int id;
    private String name;

    public PlayerSkyjoDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PlayerSkyjoDto{" + "id=" + id + ", name=" + name + '}';
    }
    
    
}
