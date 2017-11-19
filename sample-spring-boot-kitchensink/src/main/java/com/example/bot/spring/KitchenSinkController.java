/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.example.bot.spring;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.profile.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.io.ByteStreams;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.event.BeaconEvent;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.model.event.message.AudioMessageContent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.AudioMessage;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@LineMessageHandler
public class KitchenSinkController {
	private DatabaseEngine database;
	private String itscLOGIN;
	private final String GREETINGMESSAGE = "Hello" + itscLOGIN + " , welcome to dieting chatbot version1.0";
	private final String COMMANDMESSAGE = "Please choose the feature you want(Please type '1' for further operation)";
	private final String COMMAND1 = "(1)Enter Today's statistic(Please type '1' for further operation)";
	private final String COMMAND2 = "(2)Enter menu in text format(Please type '2' for further operation)";
	private final String COMMAND3 = "(3)Enter menu in JSON format(Please type '3' for further operation)";
	private final String COMMAND4 = "(4)Set remind time(Please type '4' for further operation)";
	private String preinput = "";
	private String USERID = "";
	private int mark1 = 0;
	private int mark2 = 0;
	private int mark3 = 0;
	private int mark4 = 0;
	public ReminderEngine re1;
	public ReminderEngine re2;
	public ReminderEngine re3;
	private boolean marker;
	private String[] option;
	private String[] price;
	private ArrayList<String> option2 = new ArrayList<>();
	private ArrayList<String> price2 = new ArrayList<>();
	private getCode codelist = new getCode();
	private static int icecreamnumber = 0;

	


	@Autowired
	private LineMessagingClient lineMessagingClient;

	@EventMapping
	public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
		log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		log.info("This is your entry point:");
		log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		TextMessageContent message = event.getMessage();
		handleTextContent(event.getReplyToken(), event, message);
	}

	@EventMapping
	public void handleStickerMessageEvent(MessageEvent<StickerMessageContent> event) {
		handleSticker(event.getReplyToken(), event.getMessage());
	}

	@EventMapping
	public void handleLocationMessageEvent(MessageEvent<LocationMessageContent> event) {
		LocationMessageContent locationMessage = event.getMessage();
		reply(event.getReplyToken(), new LocationMessage(locationMessage.getTitle(), locationMessage.getAddress(),
				locationMessage.getLatitude(), locationMessage.getLongitude()));
	}

	@EventMapping
	public void handleImageMessageEvent(MessageEvent<ImageMessageContent> event) throws IOException {
		final MessageContentResponse response;
		String replyToken = event.getReplyToken();
		String messageId = event.getMessage().getId();
		try {
			response = lineMessagingClient.getMessageContent(messageId).get();
		} catch (InterruptedException | ExecutionException e) {
			reply(replyToken, new TextMessage("Cannot get image: " + e.getMessage()));
			throw new RuntimeException(e);
		}
		DownloadedContent jpg = saveContent("jpg", response);
		reply(((MessageEvent) event).getReplyToken(), new ImageMessage(jpg.getUri(), jpg.getUri()));

	}

	@EventMapping
	public void handleAudioMessageEvent(MessageEvent<AudioMessageContent> event) throws IOException {
		final MessageContentResponse response;
		String replyToken = event.getReplyToken();
		String messageId = event.getMessage().getId();
		try {
			response = lineMessagingClient.getMessageContent(messageId).get();
		} catch (InterruptedException | ExecutionException e) {
			reply(replyToken, new TextMessage("Cannot get image: " + e.getMessage()));
			throw new RuntimeException(e);
		}
		DownloadedContent mp4 = saveContent("mp4", response);
		reply(event.getReplyToken(), new AudioMessage(mp4.getUri(), 100));
	}

	@EventMapping
	public void handleUnfollowEvent(UnfollowEvent event) {
		log.info("unfollowed this bot: {}", event);
	}

	@EventMapping
	public void handleFollowEvent(FollowEvent event) {
		String replyToken = event.getReplyToken();
		this.replyText(replyToken, "Got followed event");
	}

	@EventMapping
	public void handleJoinEvent(JoinEvent event) {
		String replyToken = event.getReplyToken();
		this.replyText(replyToken, "Joined " + event.getSource());
	}

	@EventMapping
	public void handlePostbackEvent(PostbackEvent event) {
		String replyToken = event.getReplyToken();
		this.replyText(replyToken, "Got postback " + event.getPostbackContent().getData());
	}

	@EventMapping
	public void handleBeaconEvent(BeaconEvent event) {
		String replyToken = event.getReplyToken();
		this.replyText(replyToken, "Got beacon message " + event.getBeacon().getHwid());
	}

	@EventMapping
	public void handleOtherEvent(Event event) {
		log.info("Received message(Ignored): {}", event);
	}

	private void reply(@NonNull String replyToken, @NonNull Message message) {
		reply(replyToken, Collections.singletonList(message));
	}

	private void reply(@NonNull String replyToken, @NonNull List<Message> messages) {
		try {
			BotApiResponse apiResponse = lineMessagingClient.replyMessage(new ReplyMessage(replyToken, messages)).get();
			log.info("Sent messages: {}", apiResponse);
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	private void replyText(@NonNull String replyToken, @NonNull String message) {
		if (replyToken.isEmpty()) {
			throw new IllegalArgumentException("replyToken must not be empty");
		}
		if (message.length() > 1000) {
			message = message.substring(0, 1000 - 2) + "..";
		}
		this.reply(replyToken, new TextMessage(message));
	}


	private void handleSticker(String replyToken, StickerMessageContent content) {
		reply(replyToken, new StickerMessage(content.getPackageId(), content.getStickerId()));
	}

	private void handleTextContent(String replyToken, Event event, TextMessageContent content)
            throws Exception {
		String text = content.getText();
		String parttext = text.substring(0,3);
		USERID = event.getSource().getUserId();
		log.info("Got text message from {}: {}", replyToken, text);
		if (mark1 == 0 && mark2 == 0 && mark3 == 0 && mark4 == 0 && !parttext.toLowerCase().equals("code")) {
			switch (text) {
				case "profile": {
					String userId = event.getSource().getUserId();
					if (userId != null) {
						lineMessagingClient
								.getProfile(userId)
								.whenComplete(new ProfileGetter(this, replyToken));
					} else {
						this.replyText(replyToken, "Bot can't use profile API without user ID");
					}
					break;
				}
				case "confirm": {
					ConfirmTemplate confirmTemplate = new ConfirmTemplate(
							"Do it?",
							new MessageAction("Yes", "Yes!"),
							new MessageAction("No", "No!")
					);
					TemplateMessage templateMessage = new TemplateMessage("Confirm alt text", confirmTemplate);
					this.reply(replyToken, templateMessage);
					break;
				}
				case "carousel": {
					String imageUrl = createUri("/static/buttons/1040.jpg");
					CarouselTemplate carouselTemplate = new CarouselTemplate(
							Arrays.asList(
									new CarouselColumn(imageUrl, "hoge", "fuga", Arrays.asList(
											new URIAction("Go to line.me",
													"https://line.me"),
											new PostbackAction("Say hello1",
													"hello 瓊嚙賤�����嚙蝓姻�嚙蝓￣�嚙蝓�")
									)),
									new CarouselColumn(imageUrl, "hoge", "fuga", Arrays.asList(
											new PostbackAction("癡穡� hello2",
													"hello 瓊嚙賤�����嚙蝓姻�嚙蝓￣�嚙蝓�",
													"hello 瓊嚙賤�����嚙蝓姻�嚙蝓￣�嚙蝓�"),
											new MessageAction("Say message",
													"Rice=癟簣糧")
									))
							));
					TemplateMessage templateMessage = new TemplateMessage("Carousel alt text", carouselTemplate);
					this.reply(replyToken, templateMessage);
					break;
				}
				// modified the reply message according to the feature you are implementing.
				case "1": {
					USERID = event.getSource().getUserId();
					this.replyText(replyToken, "Please enter your weight,height,age,sex in the format 'W:H:A:F/M");
					mark1++;
					break;
				}
				// modified the reply message according to the feature you are implementing.
				case "2":
				{
					this.replyText(replyToken, "Please input your menu (Max: 20 Options) today in the format of Option 1, Price 1, ...");
					mark2++;
					break;
				}
				// modified the reply message according to the feature you are implementing.
				case "3": {
					mark3++;
					this.replyText(replyToken, "Please enter the menu in JSONArray format");
					break;
				}
				// modified the reply message according to the feature you are implementing.
				case "4": {
					this.replyText(replyToken, "Please enter the time in the format 'HH:MM:SS', first for breakfast:");
					mark4++;
					break;
				}
				case "friend": {
					int code = codelist.getnumber();
					SQLInsertion insertcode = new SQLInsertCode(code, USERID, this);
					replyText(replyToken, "This is your invitation code" + code);
				}
				default: {
					if (!preinput.equals("1") && !preinput.equals("2") && !preinput.equals("3") && !preinput.equals("4")) {
						this.replyText(
								replyToken,
								GREETINGMESSAGE + "\n" + COMMANDMESSAGE + "\n" + COMMAND1 + "\n" + COMMAND2 + "\n" + COMMAND3 + "\n" + COMMAND4
						);
						preinput = text;
					}
				}
			}
		} else if (parttext.toLowerCase().equals("code")) {
			String code = text.substring(4);
			String userid = null;
			try {
				if (code.length() != 6) {
					throw new Exception("Illegal code!");
				}
				SQLSearching sq = new SQLSearchCode(code, this);
				USERID = sq.Search();
				if (USERID == null) {
					throw new Exception("Code Not Found!");
				}
			} catch (Exception e) {
				reminder(e.getMessage());
			}
		} else {
			// modified the 'switch' command according to the feature you are implementing.
			// modified the 'switch' command according to the feature you are implementing.
			switch(mark1) {
				case 1:{
					String[] data =text.split(":");
					int weight = Integer.parseInt(data[0]);
					int height = Integer.parseInt(data[1]);
					int age = Integer.parseInt(data[2]);
					reminder(data[3]);
					SQLInsertion re5 =new SQLInsertUserStatistic(USERID,weight,height,age,data[3], this);
					replyText(replyToken, "Thanks for using feature 1");
					break;
				}
			}
			switch(mark5) {
				case 1:{
					
					string[] data = text.split(" ");
					int fat , na , cal = 0 ;
					for (int i = 0; i < data.length; i++) {
						SQLSearching sq = new reader(data[i], this );
						string [] temp = sq.Search().text.split(" ");
						fat = fat +Integer.parseInt(temp[0]);
						cal = cal +Integer.parseInt(temp[1]);
						na = na +Integer.parseInt(temp[2]);
					}
					replyText(replyToken, "The toal consumption of " +text + "is below: fat is"+ fat + "g, calories is " + cal + "g,and sodium is "+ na +"g");
					replyText(replyToken, "Thanks for using this feature");
					break;
				}
			}
			// modified the 'switch' command according to the feature you are implementing.
			switch(mark2) {
				case 1: {
					if (re1 != null && re2 != null && re3 != null ) {
						re1.setmarker(false);
						re2.setmarker(false);
						re3.setmarker(false);
					}
					String[] input = text.split(",");
					reminder(input[0] + input[1]);
					option = new String[input.length/2];
					price = new String[input.length/2];
					for (int i = 0; i < input.length; i++) // store menu into array option & price
					{
						int j = i % 2;
						if (j == 0) {
							option[i / 2] = input[i];
						} else {
							price[(i - j) / 2] = input[i];
						}
					}
					replyText(replyToken, "Type 'yes' to check the menu you input, type 'no' if you don't want to");
					mark2++;
					break;
				}
				case 2: {
					if (text.toLowerCase().equals("yes")) {
						reminder(String.valueOf(option.length));
						for (int i = 0; i < option.length; i++) // testing if the storage is successful
						{
							reminder("option: " + option[i] + "   " + "price: " + price[i]);
						}
						mark2 = 0;
						replyText(replyToken, "Thanks for using this feature1");
					} else {
						mark2 = 0;
						replyText(replyToken, "Thanks for using this feature");
					}
					break;
				}
			}
			// modified the 'switch' command according to the feature you are implementing.
			switch(mark3) {
				case 1: {
					mark3++;
					if (re1 != null && re2 != null && re3 != null ) {
						re1.setmarker(false);
						re2.setmarker(false);
						re3.setmarker(false);
					}
					JSONArray jsonArray = JSONArray.fromObject(text);
					reminder("text");
					if (jsonArray.size() > 0) {
						for (int i = 0; i < jsonArray.size(); i++) {
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							option2.add(jsonObject.get("option").toString());
							price2.add(jsonObject.get("price").toString());
						}
					}
					replyText(replyToken, "Type 'yes' to check the menu you input, type 'no' if you don't want to");
					break;
				}
				case 2: {
					mark3 = 0;
					if (text.toLowerCase().equals("yes")) {
						for (int i = 0; i < option2.size(); i++) // testing if the storage is successful
						{
							reminder("option: " + option2.get(i) + "   " + "price: " + price2.get(i));
						}
						replyText(replyToken, "Thanks for using this feature");
					} else {
						replyText(replyToken, "Thanks for using this feature");
					}
					break;
				}
			}
			switch(mark4) {
				case 1: {
					mark4++;
					String [] time = text.split(":");
					reminder(time[0]+time[1]+time[2]);
					int hour = Integer.parseInt(time[0]);
					int minutes = Integer.parseInt(time[1]);
					int seconds = Integer.parseInt(time[2]);
					re1 = new ReminderEngine(hour, minutes, seconds,this,USERID);
					replyText(replyToken, "Then for lunch");
					break;
				}
				case 2: {
					mark4++;
					String [] time = text.split(":");
					reminder(time[0]+time[1]+time[2]);
					int hour = Integer.parseInt(time[0]);
					int minutes = Integer.parseInt(time[1]);
					int seconds = Integer.parseInt(time[2]);
					replyText(replyToken, "Then for dinner");
					re2 = new ReminderEngine(hour, minutes, seconds,this, USERID);
					break;
				}
				case 3: {
					mark4 = 0;
					String [] time = text.split(":");
					reminder(time[0]+time[1]+time[2]);
					int hour = Integer.parseInt(time[0]);
					int minutes = Integer.parseInt(time[1]);
					int seconds = Integer.parseInt(time[2]);
					re3 = new ReminderEngine(hour, minutes, seconds, this,USERID);
					replyText(replyToken, "Thanks for using this feature");
					break;
				}
			}
		}
	}

	static String createUri(String path) {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(path).build().toUriString();
	}

	private void system(String... args) {
		ProcessBuilder processBuilder = new ProcessBuilder(args);
		try {
			Process start = processBuilder.start();
			int i = start.waitFor();
			log.info("result: {} =>  {}", Arrays.toString(args), i);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (InterruptedException e) {
			log.info("Interrupted", e);
			Thread.currentThread().interrupt();
		}
	}

	private static DownloadedContent saveContent(String ext, MessageContentResponse responseBody) {
		log.info("Got content-type: {}", responseBody);

		DownloadedContent tempFile = createTempFile(ext);
		try (OutputStream outputStream = Files.newOutputStream(tempFile.path)) {
			ByteStreams.copy(responseBody.getStream(), outputStream);
			log.info("Saved {}: {}", ext, tempFile);
			return tempFile;
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	private static DownloadedContent createTempFile(String ext) {
		String fileName = LocalDateTime.now().toString() + '-' + UUID.randomUUID().toString() + '.' + ext;
		Path tempFile = KitchenSinkApplication.downloadedContentDir.resolve(fileName);
		tempFile.toFile().deleteOnExit();
		return new DownloadedContent(tempFile, createUri("/downloaded/" + tempFile.getFileName()));
	}

	public void reminder (String content) {
		PushMessage pushmessage = new PushMessage(USERID, new TextMessage(content));
		lineMessagingClient.pushMessage(pushmessage);
	}

	public KitchenSinkController() {
		database = new SQLDatabaseEngine(this);
		itscLOGIN = System.getenv("ITSC_LOGIN");
	}



	

	//The annontation @Value is from the package lombok.Valuei
	//Basically what it does is to generate constructor and getter for the class below
	//See https://projectlombok.org/features/Value
	@Value
	public static class DownloadedContent {
		Path path;
		String uri;
	}


	//an inner class that gets the user profile and status message
	class ProfileGetter implements BiConsumer<UserProfileResponse, Throwable> {
		private KitchenSinkController ksc;
		private String replyToken;
		
		public ProfileGetter(KitchenSinkController ksc, String replyToken) {
			this.ksc = ksc;
			this.replyToken = replyToken;
		}
		@Override
    	public void accept(UserProfileResponse profile, Throwable throwable) {
    		if (throwable != null) {
            	ksc.replyText(replyToken, throwable.getMessage());
            	return;
        	}
        	ksc.reply(
                	replyToken,
                	Arrays.asList(new TextMessage(
                		"Display name: " + profile.getDisplayName()),
                              	new TextMessage("Status message: "
                            		  + profile.getStatusMessage()))
        	);
    	}
    }
	
	

}
