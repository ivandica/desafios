package com.br.desafio.idwall;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.google.gson.Gson;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class Crawler {
	
	public static void main(String[] args) 
	{
		String line = "askreddit;worldnews;cats";
		boolean isRobo = false;
		if (line.substring(0, 13).equals("/NadaPraFazer")) {
			isRobo = true;
			line = line.substring(14);
		} 
		
		List<CrawlerDados> dados = new ArrayList<CrawlerDados>();
		
		List<String> threads = new ArrayList<String>();
		for (String word: line.split(";"))
	    {
			threads.add(word);
	    }
				
		WebDriver driver = new HtmlUnitDriver();
    	for (String thread : threads) {
    		driver.get("https://www.reddit.com/r/" + thread);
    		
    		List<WebElement> html = driver.findElements(By.xpath("//*[@id='siteTable']/div"));
    		for (WebElement div : html) {
    			String id = div.getAttribute("id");
    			
				if (id != null && !id.isEmpty()) {
					String upvotes = div.findElement(By.xpath("//*[@id='" + id + "']//div[1]//div[3]")).getAttribute("innerText").replaceAll("k", "000").replace(".", "");
	        		double votes = Double.parseDouble(upvotes.replaceAll("[^0-9]", "0"));
	        		if (votes > 5000) {
	        			CrawlerDados dado = new CrawlerDados();
	        			dado.setLinkComent(driver.findElement(By.xpath("//*[@id='" + id + "']//div[2]//div//ul//li[1]//a")).getAttribute("href"));
	        			dado.setLinkThread(driver.findElement(By.xpath("//*[@id='" + id + "']//div[2]//div[1]//a")).getAttribute("href"));
	        			dado.setSubreddit(driver.findElement(By.xpath("//*[@id='" + id + "']//div[2]//div/p[1]//span//a")).getAttribute("innerText"));
	        			dado.setTitThread(driver.findElement(By.xpath("//*[@id='" + id + "']//div[2]//div[1]//a")).getAttribute("innerText"));
	        			dado.setUpvotes(driver.findElement(By.xpath("//*[@id='" + id + "']//div[1]//div[3]")).getAttribute("innerText"));
	        			dados.add(dado);
	        			
	        			System.out.println("Upvotes.........: " + dado.getUpvotes());
	            		System.out.println("Subreddit.......: " + dado.getSubreddit());
	            		System.out.println("Título Thread...: " + dado.getTitThread());
	                	System.out.println("Link Thread.....: " + dado.getLinkThread());
	                	System.out.println("Link Comentário.: " + dado.getLinkComent());
	        		}
				}
    		}
    	}
		
    	driver.quit();
    	
    	if (isRobo) {
	    	TelegramBot bot = TelegramBotAdapter.build("444095557:AAEAqVcL9KnGCLP-Jf--QxEvwZ3XvXmS9Ps");
	
	    	GetUpdatesResponse updatesResponse;
	    	SendResponse sendResponse;
	    	BaseResponse baseResponse;
	    			
	    	int m = 0;
	    			
			updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(m));
			
			List<Update> updates = updatesResponse.updates();
	
			for (Update update : updates) {
				
				m = update.updateId() + 1;
				
				System.out.println("Recebendo mensagem: " + update.message().text());
				
				baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
				System.out.println("Resposta de Chat Action Enviada? " + baseResponse.isOk());
				
				Gson gson = new Gson();
		        String json = gson.toJson(dados);
		        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), json));
				System.out.println("Mensagem Enviada? " + sendResponse.isOk());
			}
    	}
	}
}