/* Create a new modal dialog with the given title and contents */
/* Updated 2011/03/6: for use with jQuery */
function Modal(title, contents, hashID) {
    
    this.div = null;
    this.eTitle = null;
    this.eContents = null;
    this.eButtons = null;
    
    this.txttitle = title;
    this.txtcontents = contents;
    this.hashID = hashID;
    
    // true iff the modal window is being displayed
    this.displayed = false;
    
    var generateHash = function(length) {
        // generate a random hex hash of given length
        var h = [];
        for (var i=0; i<length; i++)
            h.push((Math.random()*16|0).toString(16));

        return h.join("");
    };

    /* init */
    if ( hashID == null ) this.hashID = "modal" + generateHash(5);
    if ( this.txttitle == null ) this.txttitle = "";
    if ( this.txtcontents == null ) this.txtcontents = "<br />";
    
    // generate the elements for our modal
    this.div = $('<div />')
                    .attr('id', this.hashID)
                    .addClass('modal');
    this.div.themodal = this;
    
    var posDiv = $("<div />")
        .addClass("mpos");
    this.div.append(posDiv);

    var container = $('<div />')
                        .addClass('mcontainer');
    posDiv.append(container);
    
    this.eTitle = $("<h1 />")
                        .html(this.txttitle);
    container.append(this.eTitle);
    
    this.eContents = $("<div />")
                        .addClass('mcontent')
                        .html(this.txtcontents);
    container.append(this.eContents);
    
    this.eButtons = $("<div />")
                        .addClass('mbuttons');
    // container.append(this.eButtons);
    posDiv.append(this.eButtons);
    

    
    // posDiv.append(this.div);
    $("body").append(this.div);
}

Modal.method('addbutton', function (text, link, title) {
    /**
     * Add a button to this window
     */    
    if ( link == null ) link = '#';
    if ( title == null ) title = text;
    
    var btn = $('<a />')
        .attr('href', link)
        .attr('title', title)
        .html(text);
    this.eButtons.append(btn);
    
    return btn;
});

Modal.method('show', function () {
    /**
     * Display this modal window
     */
    location.hash = this.hashID;
    this.displayed = true;
});

Modal.method('hide', function () {
    /**
     * Hide this modal window
     */
    location.hash = '';
    this.displayed = false;
});