package ua.nure.kn.susidskyi.agent;

import ua.nure.kn.susidskyi.db.DataBaseException;

public class SearchExeption {

	public SearchException(DataBaseException e) {
		e.printStackTrace();
	}
}
