Developed and Instructed by Micah

1. Create your Settings.java, this class is what we will be using to read and write to our 
settings files. It also creates and manages
the development of the files, as if they are created or not. 
This part is very important. Find the file 
here https://github.com/thund3rboi/ClientMods/blob/master/settings-tutorial/Settings.java

2. Lets just go over a few things inside of this Settings.java 
and what they do, its all code from Eric Golde so if you need a better explanation I suggest checking out this 
video https://www.youtube.com/watch?v=A0Yfw1TWAhM&list=PLxbv-Ej1VQMQS9M2qnmEQtp-qL3xcA4ua&index=15

 • init() - This is what will create all of the files/folders. Make sure to put this 
 somewhere on your Client Enable
 • writeJsonToFile(File file, Class<T> c) - This will write something to a file, weither its a 
 boolean, or a string. In this case it will always be a boolean.
 • readFromJson(File file, Class<T> c) - This will read something from a file. We will be using this to read our booleans

So thats a simple explanation of the things you can find inside of this class.

3. Now we need to create an enum to hold all of our Mods and there booleans/config. So lets create a new Enum called SettingsConf.java and that can be found here https://github.com/thund3rboi/ClientMods/tree/master/settings-tutorial

4. Now lets go over some things inside of this class...

 • EXAMPLE_ENUM(boolean, String) - So this is each Mod. You will put all your mods here with 2 args, the boolean which displays if you
 are using the mod or not. As always true means you are using the mod and false means you are not. The second is a display name for the
 mod. This is used to keep track of the Files and stuff like that.
 • getDefaultValue() - This one isn't to important but I am explaining it so that you can see the different between this one and the next
 one. This one will get the value inside of the Enum, pretty simple.
 • getVauleFromSavedConf(SettingsConf conf) - So this is where we will load the value from the file. This is so that you can toggle the
 values instead of only gettings the default one. So we are going to use the readFromJson in our Settings.java and we are using
 boolean.class to pass in an instance of a class.
 • getConfigFolder() - This will create/get your mods settings folder. Use this when needed. If created the folder and file based on the
 displayString arg.
 
So those are the important things inside of that class. Now lets use this inside of a GUI/Module Manager

5. Lets create a simple button manager using Eric Golde's 
https://github.com/egold555/MCP-Snippets/tree/master/GuiCheckBox and GuiButton.class, I 
have already created the code and you can get it here https://github.com/thund3rboi/ClientMods/blob/master/settings-tutorial/GuiModManager.java, okay lets go over a few things.
 
 • This class is pretty much just GuiButton.java with like 2 or 3 things from GuiCheckBox.java so its not to complicated.
 • This will display On/Off according to the boolean given. If its true, it will say on and so, so.
 
Now we can create a simple class using this button.

6. Create a class and just name it like GuiYourClientSettings and I have already done that here https://github.com/thund3rboi/ClientMods/blob/master/settings-tutorial/GuiMyClientSettings.java, so we can now explain some things from this class :D

 • swapValues(SettingsConf conf, IRenderer modInstance) - This is what will swap the boolean (I.e. if its false it will turn to true)
 and we use the modInstance to unregister and register the Mod so that we 
 do not have duplicates. We are simply just using
 #getValueFromSavedConf(SettingsConf, boolean) to get the boolean 
 to see what we need to swap it to.
 
So that is it! I hope that this works for you, I spent a lot of time on this and I hope it works well as it works amazing for me. I am offering support so if you need help feel free to message me on discord @ Meecka#7099

################################################ Saving the screen poition of a mod to the settings file ###############################

This is something I ran into while making this so if you need a fix its quite simple.

Only use this if you are trying to save the ScreenPosition of your mods.

Inside of your ModDraggable.java add a constructor like this...

public ModDraggable(SettingsConf conf) {
		this.conf = conf;
		pos = loadPositionFromFile(conf);
}

Now you should already have loadPositionFromFile() but you will need to add SettingsConf as an argument to that and to the save method.

Then you can change your load method to this...

private ScreenPosition loadPositionFromFile(SettingsConf conf) {
		ScreenPosition loaded = Settings.readFromJson(new File(getFolder(conf),conf.getDisplayString() + "pos.json"), ScreenPosition.class);
		if (loaded == null) {
			loaded = ScreenPosition.fromRelativePosition(0.5, 0.5);
			this.pos = loaded;
			savePositionToFile(conf);
		}
		return loaded;
	}
  
This is just an easier way to keep it more organized. You can keep your mod boolean and ScreenPosition in the same folder. Get the folder by doing...

private static File getFolder(SettingsConf conf) {
		File folder = new File(Settings.getSettingsDir(), conf.getDisplayString());
		folder.mkdirs();
		return folder;
}

This should work, now you just need to add the constructor to each class. For example we used an FPS mod so add this to your FPS Mod class...

public ModFPS(SettingsConf conf) {
		super(conf);
		conf = SettingsConf.FPS;
}
  
Awesome! Thats it! :D
