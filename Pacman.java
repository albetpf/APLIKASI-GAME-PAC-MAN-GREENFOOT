import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Pacman extends Actor
{
    //variabel untuk foto, nyawa, skor, point, waktu dan level
    private GreenfootImage pacmanimage1 = new GreenfootImage("pacman.png");
    private GreenfootImage pacmanimage2 = new GreenfootImage("pacman2.png");
    private GreenfootImage gameoverimage = new GreenfootImage("gameover.png");
    private GreenfootImage wingameimage = new GreenfootImage("youwin.png");
    private int lives = 3;
    private int score;
    private int pointEaten;
    private int timer;
    private int level = 1;
    public Pacman()
    {
        //memberi waktu 1000 detik
        timer = 1000;
    }
    public void act() 
    {
        //gerakan untuk aktor Pacman
        pacmanMove();
        detectGhost();
        detectPortal();
        eatPoint();
        showStatus();
        youWin();
        updateTimer();
    }
    public void animate()
    {
        //foto untuk animasi gerakan aktor Pacman
        if(getImage()==pacmanimage1){
            setImage(pacmanimage2);
        }
        else{
            setImage(pacmanimage1);
        }
    }
    public void pacmanMove()
    {
        //metode pergerakan aktor Pacman menggunakan arrow key
        if(Greenfoot.isKeyDown("right")){
            setLocation(getX()+4, getY());
            setRotation(360);
            animate();
            Actor wall = getOneIntersectingObject(Wall.class);
            if (wall !=null)
            {
                setLocation (getX()-4,getY());
            }
        }
        if(Greenfoot.isKeyDown("left")){
            setLocation(getX()-4, getY());
            setRotation(180);
            animate();
            Actor wall = getOneIntersectingObject(Wall.class);
            if (wall !=null)
            {
                setLocation (getX()+4,getY());
            }
        }
        if(Greenfoot.isKeyDown("down")){
            setLocation(getX(), getY()+4);
            setRotation(90);
            animate();
            Actor wall = getOneIntersectingObject(Wall.class);
            if (wall !=null)
            {
                setLocation (getX(),getY()-4);
            }
        }
        if(Greenfoot.isKeyDown("up")){
            setLocation(getX(), getY()-4);
            setRotation(-90);
            animate();
            Actor wall = getOneIntersectingObject(Wall.class);
            if (wall !=null)
            {
                setLocation (getX(),getY()+4);
            }
        }
    }
    public void detectGhost()
    {
        //ketika aktor Pacman bertemu dengan aktor Ghost
        if(isTouching(Ghost.class)){
            Greenfoot.playSound("hurt03.wav");
            setLocation(28,39);
            removeLife();
        }
    }
    public void detectPortal()
    {
        //ketika aktor Pacman bertemu dengan aktor Portal
        if(isTouching(Portal.class)){
            level++;
            Greenfoot.playSound("cartoon-yuppie.wav");
            setLocation(28,39);
            MyWorld myworld = (MyWorld)getWorld();
            myworld.increaseLevel();
            timer = 1000;
        }
    }
    public void eatPoint()
    {
        //ketika aktor Pacman bertemu dengan aktor Point
        if(isTouching(Point.class)){
            Greenfoot.playSound("nom.wav");
            removeTouching(Point.class);
            increaseScore();
            timer = timer + 200;
        }
    }
    public void removeLife()
    {
        //ketika aktor Pacman memulai permainan baru
        lives--;
        timer = 1000;
        gameOver();
        showStatus();
    }
    public void youWin()
    {
        //ketika dilevel 3 dan menyelesaikan game
        if(level > 3){
            setImage(wingameimage);
            Greenfoot.playSound("you win.wav");
            setLocation(422,353);
            setRotation(360);
            Greenfoot.stop();
        }
    }
    public void gameOver()
    {
        //ketika kalah
        if (lives <= 0) {
            setImage(gameoverimage);
            Greenfoot.playSound("game over.wav");
            setLocation(422,353);
            setRotation(360);
            Greenfoot.stop();
        }
    }
    public void increaseScore()
    {
        //menambahkan skor ketika memakan Point
        score+=10;
        showStatus();
    }
    public void showStatus()
    {
        //menampilkan status untuk level, nyawa dan skor
        getWorld().showText("Level : "+level, 85, 520);
        getWorld().showText("Lives : "+lives, 85, 540);
        getWorld().showText("Score : "+score, 85, 560);
    }
    public void updateTimer()
    {
        //memperbaru waktu ketika memasuki dunia/level baru
        timer--;
        getWorld().showText("Time Left = "+timer, 85, 580);
        if(timer < 1){
            removeLife();
            Greenfoot.playSound("hurt03.wav");
            setLocation(28,39);
        }
    }
}
