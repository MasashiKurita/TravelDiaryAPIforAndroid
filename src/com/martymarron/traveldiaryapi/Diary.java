package com.martymarron.traveldiaryapi;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Diary {
	
	@SerializedName("id")
	private long id;
	
	@SerializedName("user_id")
	private String userId;
	
	@SerializedName("title")
	private String title;
	
	@SerializedName("description")
	private String description;
	
	@SerializedName("milestones")
	private List<MileStone> milestones = new ArrayList<MileStone>();
	
	public Diary(long id, String userId, String title, String description) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.description = description;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the user_id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the milestones
	 */
	public List<MileStone> getMilestones() {
		return milestones;
	}

	/**
	 * @param milestones the milestones to set
	 */
	public void setMilestones(List<MileStone> milestones) {
		this.milestones = milestones;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getTitle() + "\n" 
	+ this.getDescription() + "\n"
	+ Integer.toString(this.getMilestones().size()) + " milestones.";
	}

	public static class MileStone {
		
		@SerializedName("id")
		private long id;
		
		@SerializedName("page_id")
		private String pageId;
		
		@SerializedName("diary")
		private long diary;
		
		public MileStone(long id, String pageId, long diary) {
			this.id = id;
			this.pageId = pageId;
			this.diary = diary;
		}

		/**
		 * @return the id
		 */
		public long getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(long id) {
			this.id = id;
		}

		/**
		 * @return the page_id
		 */
		public String getPageId() {
			return pageId;
		}

		/**
		 * @param page_id the page_id to set
		 */
		public void setPageId(String pageId) {
			this.pageId = pageId;
		}

		/**
		 * @return the diary
		 */
		public long getDiary() {
			return diary;
		}

		/**
		 * @param diary the diary to set
		 */
		public void setDiary(long diary) {
			this.diary = diary;
		}
	}

}
