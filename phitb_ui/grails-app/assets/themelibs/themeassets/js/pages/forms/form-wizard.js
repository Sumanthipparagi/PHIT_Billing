$(function () {
    //Horizontal form basic
    $('#wizard_horizontal').steps({
        headerTag: 'h2',
        bodyTag: 'section',
        transitionEffect: 'slideLeft',
        onInit: function (event, currentIndex) {
            setButtonWavesEffect(event);
        },
        onStepChanged: function (event, currentIndex, priorIndex) {
            setButtonWavesEffect(event);
        }
    });

    //Vertical form basic
    $('#wizard_vertical').steps({
        headerTag: 'h2',
        bodyTag: 'section',
        transitionEffect: 'slideLeft',
        stepsOrientation: 'vertical',
        onInit: function (event, currentIndex) {
            setButtonWavesEffect(event);
        },
        onStepChanged: function (event, currentIndex, priorIndex) {
            setButtonWavesEffect(event);
        }
    });

    //Advanced form with validation
    var form = $('#wizard_with_validation').show();
    form.steps({
        headerTag: 'h3',
        bodyTag: 'fieldset',
        transitionEffect: 'slideLeft',
        onInit: function (event, currentIndex) {
            $.AdminAlpino.input.activate();

            //Set tab width
            var $tab = $(event.currentTarget).find('ul[role="tablist"] li');
            var tabCount = $tab.length;
            $tab.css('width', (100 / tabCount) + '%');

            //set button waves effect
            setButtonWavesEffect(event);
        },
        onStepChanging: function (event, currentIndex, newIndex) {
            if (currentIndex > newIndex) {
                return true;
            }

            if (currentIndex < newIndex) {
                form.find('.body:eq(' + newIndex + ') label.error').remove();
                form.find('.body:eq(' + newIndex + ') .error').removeClass('error');
            }

            form.validate().settings.ignore = ':disabled,:hidden';
            return form.valid();
        },
        onStepChanged: function (event, currentIndex, priorIndex) {
            setButtonWavesEffect(event);
        },
        onFinishing: function (event, currentIndex) {
            form.validate().settings.ignore = ':disabled';
            return form.valid();
        },
        onFinished: function (event, currentIndex) {
            // Swal.fire("Good job!", "Submitted!", "success");
            // $('#wizard_with_validation').submit();
            var indexed_array = {};
            var form = $('#wizard_with_validation').serializeArray();
            $.map(form, function (n, i) {
                indexed_array[n['name']] = n['value'];
            });
            // console.log(indexed_array)
            $.ajax({
                method: "POST",
                url: "/save-entity-onboard-info",
                data: indexed_array,
                success: function (data) {
                    if (data.priority === 200) {
                        Swal.fire({
                            title: "Success!",
                            text: "Priority saved successfully!",
                            type: "success"
                        }).then(function() {
                            if (data.division === 200) {
                                Swal.fire({
                                    title: "Success!",
                                    text: "Division saved successfully!",
                                    type: "success"
                                }).then(function() {
                                    if (data.series === 200) {
                                        Swal.fire({
                                            title: "Success!",
                                            text: "Series saved successfully!",
                                            type: "success"
                                        })
                                    } else {
                                        Swal.fire("Series save failed!")
                                    }
                                });
                            } else {
                                Swal.fire("Division save failed!")
                            }
                        });
                    } else {
                        Swal.fire("Priority save failed!")
                    }
                },
                error: function (data) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Something went wrong!',
                        // footer: '<a href="">Why do I have this issue?</a>'
                    });
                }
            })
        }
    });

    form.validate({
        highlight: function (input) {
            $(input).parents('.form-line').addClass('error');
        },
        unhighlight: function (input) {
            $(input).parents('.form-line').removeClass('error');
        },
        errorPlacement: function (error, element) {
            $(element).parents('.form-group').append(error);
        },
        rules: {
            'confirm': {
                equalTo: '#password'
            }
        }
    });
});

function setButtonWavesEffect(event) {
    $(event.currentTarget).find('[role="menu"] li a').removeClass('waves-effect');
    $(event.currentTarget).find('[role="menu"] li:not(.disabled) a').addClass('waves-effect');
}