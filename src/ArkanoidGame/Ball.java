package ArkanoidGame;

public class Ball {
    private int ballposX = Constants.ballposX;
    private int ballposY = Constants.ballposY;
    private int ballxDir = Constants.ballxDir;
    private int ballyDir = Constants.ballyDir;

    public void setBallposX(int ballposX) {
        this.ballposX = ballposX;
    }

    public void setBallposY(int ballposY) {
        this.ballposY = ballposY;
    }

    public void setBallxDir(int ballxDir) {
        this.ballxDir = ballxDir;
    }

    public void setBallyDir(int ballyDir) {
        this.ballyDir = ballyDir;
    }

    public int getBallposX() {
        return ballposX;
    }

    public int getBallposY() {
        return ballposY;
    }

    public int getBallxDir() {
        return ballxDir;
    }

    public int getBallyDir() {
        return ballyDir;
    }
}
