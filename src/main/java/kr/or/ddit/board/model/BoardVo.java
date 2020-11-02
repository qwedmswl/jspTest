package kr.or.ddit.board.model;

public class BoardVo {
	
	private int boardNo;
	private String title;
	private String content;
	
	public BoardVo(int boardNo, String title, String content) {
		
		// 인자가 있는 생성자를 만들어준다.
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
	}
	
	public BoardVo() {
		
		// 기본 생성자 (대다수 framework에서 요구를 함)
	}
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "BoardVo [boardNo=" + boardNo + ", title=" + title + ", content=" + content + "]";
	}
}
