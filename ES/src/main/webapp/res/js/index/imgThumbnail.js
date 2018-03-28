function DrawImage(obj) {
    $(obj).jqthumb({
        classname: 'jqthumb',
        width: '100%',
        height: '100%',
        position: {y: '50%', x: '50%'},
        zoom: '1',
        method: 'auto',
    });
}

function DrawImage2(obj) {
    $(obj).jqthumb({
        classname: 'jqthumb',
        width: '140px',
        height: '140px',
        position: {y: '50%', x: '50%'},
        zoom: '1',
        method: 'auto',
        after: function (obj) {      // callback when each image is cropped.
            $(obj).addClass("img-circle")
        },
    });
}

function DrawImage3(obj, width, height) {
    $(obj).jqthumb({
        classname: 'jqthumb',
        width: '500px',
        height: '280px',
        position: {y: '50%', x: '50%'},
        zoom: '1',
        method: 'auto',
        after: function (obj) {      // callback when each image is cropped.
            $(obj).addClass("img-responsive")
        },
    });
}