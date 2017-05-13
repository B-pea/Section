package com.Section.view;

public class query_parameter_entity
{
	private String tname = "";

	private String columns = "";

	private String rowkey = "";

	public String getTname()
	{
		return tname;
	}

	public void setTname(String tname)
	{
		this.tname = tname;
	}

	public String getColumns()
	{
		return columns;
	}

	public void setColumns(String columns)
	{
		this.columns = columns;
	}

	public String getRowkey()
	{
		return rowkey;
	}

	public void setRowkey(String rowkey)
	{
		this.rowkey = rowkey;
	}

	public String toString()
	{
		return "{\"tname\":\"" + tname + "\", \"columns\":\"" + columns + "\", \"rowkey\":\"" + rowkey + "\"}";
	}

}
