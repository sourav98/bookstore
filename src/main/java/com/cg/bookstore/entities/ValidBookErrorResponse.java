package com.cg.bookstore.entities;

import java.time.LocalDate;

public class ValidBookErrorResponse
{
	private int status;
	private String message;
	private LocalDate timeStamp;
	private String details;

	public ValidBookErrorResponse()
	{

	}

	public ValidBookErrorResponse(int status, String message, LocalDate timeStamp, String details)
	{
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
		this.details = details;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public LocalDate getTimeStamp()
	{
		return timeStamp;
	}

	public void setTimeStamp(LocalDate timeStamp)
	{
		this.timeStamp = timeStamp;
	}

	public String getDetails()
	{
		return details;
	}

	public void setDetails(String details)
	{
		this.details = details;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("ValidBookErrorResponse [status=");
		builder.append(status);
		builder.append(", message=");
		builder.append(message);
		builder.append(", timeStamp=");
		builder.append(timeStamp);
		builder.append(", details=");
		builder.append(details);
		builder.append("]");
		return builder.toString();
	}

}
