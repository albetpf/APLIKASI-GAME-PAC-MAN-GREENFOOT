import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Ghost2 extends Actor
{
    //variabel untuk menentukan kecepatan dan arah gerakan hantu
    public int speed = 2;
    public String direction = "down";
    //metode yang dipanggil setiap saat untuk gerakan aktor hantu
    public void act() 
    {
        //mengatur lokasi aktor hantu ke bawah sesuai dengan kecepatan
        setLocation(getX(), getY() + speed);
        //memeriksa apakah aktor hantu bertemu dengan dinding
        atWall();
    }
    //metode untuk mengubah arah gerakan hantu
    public void changeDirection()
    {
        //jika arah sekarang adalah "down", ubah menjadi "up"
        if (direction.equals("down")){
            direction = "up";
        }
    }
    //metode untuk mendeteksi ketika hantu berada di dekat dinding
    public void atWall()
    {
        Actor wall = getOneIntersectingObject(Wall.class);
        //ketika hantu bersentuhan dengan dinding
        if (wall != null){
            // Memutar arah gerakan dan mengubah arah hantu
            speed = -speed;
            changeDirection();
        }
    }
}
