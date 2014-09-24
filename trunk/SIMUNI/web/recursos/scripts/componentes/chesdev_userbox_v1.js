var style_text = ".chesdev_component_userbox_link{font-size: 1.2em;font-family: sans-serif;color: gray;text-decoration: none;}" +
        ".chesdev_component_userbox_item{padding: 9px;}" +
        ".chesdev_component_userbox_link,.chesdev_component_userbox_link:visited,.chesdev_component_userbox_link:active{color: gray;}" +
        ".chesdev_component_userbox_link:hover{color:black;}#chesdev_component_userbox_taguser{font-family: sans-serif; font-size: 1.1em;color: black;}" +
        "#chesdev_component_userbox_item_container{margin-top: 20px;}" +
        "#chesdev_component_userbox_container,#chesdev_component_userbox_generalcontainer{padding: 10px;width:300px;}" +
        "#chesdev_component_userbox_generalcontainer{background-color:green;width:330px;}"+
        ".chesdev_component_userbox_link{padding: 25px;font-size: 1.25em;}" +
        "#chesdev_component_userbox_container{border: solid black 0,5px;}" +
        "#chesdev_component_userbox_taguser{float: right;bottom: 0px;}";
var chesdev_userbox = {
    state_flag: false,
    chesdev_userbox: function() {
    },
    user_text: "Opciones de Usuario",
    elements: [],
    X: 0,
    Y: 0,
    addMenuItem: function(item_link, item_name) {
        this.elements[this.elements.length] = item_link + "," + item_name;
        // alert(this.elements[this.elements.length - 1]);
    },
    removeMenuItems: function() {
        while (this.elements.length > 0) {
            this.elements.pop();
        }
    },
    setUserText: function(user_text) {
        this.user_text = user_text;
    },
    setXYPos: function(x, y) {
        this.X = x;
        this.Y = y;
    },
    getDivPosition: function() {
        return  "#chesdev_component_userbox_generalcontainer{position: absolute; left: " + this.X + "px; top: " + this.Y + "px; }";
    },
    getStyle1: function() {
        return style_text + this.getDivPosition();
    },
    printElements: function() {
        var styletag = document.getElementById('chesdev_component_userbox_element');
        if (styletag == null) {
            var chesdev_components_stylecontainertext = document.createTextNode(this.getStyle1());
            var chesdev_components_stylecontainer = document.createElement("style");
            chesdev_components_stylecontainer.appendChild(chesdev_components_stylecontainertext); //add the text node to the newly created div. 
            var chesdev_components_container = document.createElement("div");
            chesdev_components_container.id = "chesdev_components_container";
            chesdev_components_stylecontainer.id = "chesdev_component_userbox_styletag";
            var chesdev_components_innercontainer = document.createElement("div");
            chesdev_components_innercontainer.id = "chesdev_component_userbox_element";
            chesdev_components_innercontainer.appendChild(chesdev_components_stylecontainer);
            chesdev_components_container.appendChild(chesdev_components_innercontainer);
            document.body.appendChild(chesdev_components_container);

        }
        divcontainer = document.getElementById("chesdev_component_userbox_element");
        divcontainer.innerHTML = "";
        var chesdev_components_stylecontainertext = document.createTextNode(this.getStyle1());

        var chesdev_components_stylecontainer = document.createElement("style");
        chesdev_components_stylecontainer.id = "chesdev_component_userbox_styletag";
        chesdev_components_stylecontainer.appendChild(chesdev_components_stylecontainertext); //add the text node to the newly created div. 


        divcontainer.appendChild(chesdev_components_stylecontainer);
        text_to_add = "<div id='chesdev_component_userbox_generalcontainer'>" +
                "<fieldset id='chesdev_component_userbox_container'>" +
                "<legend id='chesdev_component_userbox_taguser'>" + this.user_text + "</legend>" +
                "<div id='chesdev_component_userbox_item_container'>";
        for (a = 0; a < this.elements.length; a++) {

            text_to_add += "<div class='chesdev_component_userbox_item'>" +
                    "<span class='chesdev_component_userbox_defaultlabel'>&Gt;" +
                    "<a href='" + this.elements[a].split(',')[0] + "' class='chesdev_component_userbox_link'>" +
                    this.elements[a].split(',')[1] +
                    "</a>" +
                    "</span>" +
                    "</div>";

        }
        divcontainer.innerHTML += text_to_add;

    },
    hideComponent: function() {
        elementtohide = document.getElementById('chesdev_components_container');
        if (elementtohide !== null) {
            elementtohide.innerHTML = "";
            elementtohide.parentNode.removeChild(elementtohide)
        }
    }
};



var chesdev_components_userbox_handler = {
    chesdev_userbox_object: chesdev_userbox,
    chesdev_components_events_handler: function(event) {
        if (this.chesdev_userbox_object == null) {
            this.chesdev_userbox_object = chesdev_userbox;
        }
        if (!this.chesdev_userbox_object.state_flag) {
            this.chesdev_userbox_object.state_flag = true;
            var x;
            var y;
            var source = event.target || event.srcElement;
            //alert(source.offsetLeft+source.offsetWidth);
            if (event.pageX || event.pageY) {
                x = event.pageX;
                y = event.pageY;
            }
            else {
                x = event.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
                y = event.clientY + document.body.scrollTop + document.documentElement.scrollTop;
            }

            x = source.offsetLeft + source.offsetWidth;
            y = source.offsetTop + source.offsetHeight;
            this.chesdev_userbox_object.setXYPos(x - 350, y);
            this.chesdev_userbox_object.printElements();

        }
        else {
            this.chesdev_userbox_object.state_flag = false;
            this.chesdev_userbox_object.hideComponent();
        }
    },
    addMenuItem: function(item_link, item_name) {
        this.chesdev_userbox_object.addMenuItem(item_link, item_name);
    },
    removeMenuItems: function() {
        this.chesdev_userbox_object.removeMenuItems();
    },
    setUserText: function(user_text) {
        this.chesdev_userbox_object.setUserText(user_text);
    },
    setXYPos: function(x, y) {
        this.chesdev_userbox_object.setXYPos(x, y);
    },
    printElements: function() {
        this.chesdev_userbox_object.printElements();
    },
    hideComponent: function() {
        this.chesdev_userbox_object.hideComponent();
    }
};
