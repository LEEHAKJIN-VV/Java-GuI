package gui;

public class UserPilot {
    Vector userVec; // UserPiolt의 vec 좌표
    
    public UserPilot() { // 화면 정중앙에 처음위치
    	this.userVec = new Vector(MainFrame.WIDTH/2,MainFrame.HEIGHT/2);
    }
}
