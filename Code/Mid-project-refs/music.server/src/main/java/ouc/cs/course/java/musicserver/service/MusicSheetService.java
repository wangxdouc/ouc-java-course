package ouc.cs.course.java.musicserver.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ouc.cs.course.java.musicserver.dao.MusicDao;
import ouc.cs.course.java.musicserver.dao.MusicSheetDao;
import ouc.cs.course.java.musicserver.dao.MusicSheetToMusicDao;
import ouc.cs.course.java.musicserver.dao.impl.MusicDaoImpl;
import ouc.cs.course.java.musicserver.dao.impl.MusicSheetDaoImpl;
import ouc.cs.course.java.musicserver.dao.impl.MusicSheetToMusicDaoImpl;
import ouc.cs.course.java.musicserver.model.Music;
import ouc.cs.course.java.musicserver.model.MusicSheet;
import ouc.cs.course.java.musicserver.model.MusicSheetToMusic;

public class MusicSheetService {

	MusicSheetDao musicSheetDao = new MusicSheetDaoImpl();
	MusicDao musicDao = new MusicDaoImpl();
	MusicSheetToMusicDao mstmDao = new MusicSheetToMusicDaoImpl();

	public MusicSheetService() {
	}

	public int create(MusicSheet ms) throws SQLException {
		return musicSheetDao.insert(ms);
	}

	public void createOrUpdate(MusicSheet ms) throws SQLException {

		MusicSheet musicSheet = musicSheetDao.findByUuid(ms.getUuid());

		Music mu = null;
		MusicSheetToMusic mstm = null;
		int musicSheetId, musicId;

		if (musicSheet == null) {
			musicSheetId = musicSheetDao.insert(ms);

			for (String key : ms.getMusicItems().keySet()) {
				mu = musicDao.findByMd5value(key);

				if (mu == null) {
					mu = new Music(key, ms.getMusicItems().get(key));
					musicId = musicDao.insert(mu);

				} else {
					mu.setName(ms.getMusicItems().get(key));
					musicDao.update(mu);
					musicId = mu.getId();
				}

				mstm = new MusicSheetToMusic(musicSheetId, musicId);
				mstmDao.insert(mstm);
			}

		} else {
			musicSheetDao.update(ms);
			musicSheetId = musicSheet.getId();

			musicSheet.getMusicItems();

			for (String key : ms.getMusicItems().keySet()) {
				mu = musicDao.findByMd5value(key);

				if (mu == null) {
					mu = new Music(key, ms.getMusicItems().get(key));
					musicId = musicDao.insert(mu);
					mstm = new MusicSheetToMusic(musicSheetId, musicId);
					mstmDao.insert(mstm);

				} else {
					mu.setName(ms.getMusicItems().get(key));
					musicDao.update(mu);
				}
			}
		}
	}

	public List<MusicSheet> getAll() throws SQLException {
		List<MusicSheet> mslist = musicSheetDao.findAll();

		Music mu = null;
		for (MusicSheet ms : mslist) {
			Map<String, String> musicItems = new HashMap<String, String>();

			for (int musicId : mstmDao.findByMusicSheetId(ms.getId())) {
				mu = musicDao.findById(musicId);
				musicItems.put(mu.getMd5value(), mu.getName());
			}

			ms.setMusicItems(musicItems);
		}

		return mslist;
	}
	
	/**
	 * 获取尊新的num条记录
	 * @param num
	 * @return
	 * @throws SQLException
	 */
	public List<MusicSheet> getLatest(int num) throws SQLException {
		List<MusicSheet> mslist = musicSheetDao.findLatest(num);

		Music mu = null;
		for (MusicSheet ms : mslist) {
			Map<String, String> musicItems = new HashMap<String, String>();

			for (int musicId : mstmDao.findByMusicSheetId(ms.getId())) {
				mu = musicDao.findById(musicId);
				musicItems.put(mu.getMd5value(), mu.getName());
			}

			ms.setMusicItems(musicItems);
		}

		return mslist;
	}
	
	

	public String getMusicSheetPictureUrl(String uuid) throws SQLException {
		MusicSheet musicSheet = musicSheetDao.findByUuid(uuid);
		return musicSheet.getPicture();
	}

	public void delete(String uuid) throws SQLException {
		musicSheetDao.deleteByUuid(uuid);
	}
}
