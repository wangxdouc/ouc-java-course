package ouc.cs.course.java.musicserver.service;

import java.sql.SQLException;

import ouc.cs.course.java.musicserver.dao.MusicSheetDao;
import ouc.cs.course.java.musicserver.dao.MusicSheetToMusicDao;
import ouc.cs.course.java.musicserver.dao.impl.MusicSheetDaoImpl;
import ouc.cs.course.java.musicserver.dao.impl.MusicSheetToMusicDaoImpl;
import ouc.cs.course.java.musicserver.model.MusicSheet;
import ouc.cs.course.java.musicserver.model.MusicSheetToMusic;

public class MusicSheetToMusicService {

	MusicSheetToMusicDao mstmDao = new MusicSheetToMusicDaoImpl();

	public MusicSheetToMusicService() {
	}

	public int create(MusicSheetToMusic mstm) throws SQLException {
		return mstmDao.insert(mstm);
	}

}
