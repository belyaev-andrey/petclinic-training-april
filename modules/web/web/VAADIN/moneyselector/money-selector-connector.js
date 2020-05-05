com_company_clinic_web_ui_components_jscomponent_MoneySelector = function () {
    var connector = this;
    var element = connector.getElement();
    element.innerHTML = "<input type=\"text\" id=\"currency\"/>";

    var moneySelector;

    this.onStateChange = function () {
        var data = this.getState().data;

        var options = {
            prefix: data.prefix,
            suffix: data.suffix,
            onValueChange: function () { //Invoked on blur event in the component. See function blurEvent(e) in the component JS file.
                connector.onValueChange(moneySelector.maskMoney('unmasked')[0])
            }
        };

        moneySelector = $('#currency').maskMoney(options)

    };

};
