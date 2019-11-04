package com.isty.arlo.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Creneau
{
	private String id;
    private Date start;
    private Date end;
    

    public Creneau(String id, Date start, Date end)
    {
    	this.id = id;
        this.start = start;
        this.end = end;
    }
    
    

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStart()
    {
        return start;
    }

    public void setStart(Date start)
    {
        this.start = start;
    }

    public Date getEnd()
    {
        return end;
    }

    public void setEnd(Date end)
    {
        this.end = end;
    }
    
    public String getStrStart() {
    	return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(start);
    }
    
    public String getStrEnd() {
    	return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(end);
    }
}