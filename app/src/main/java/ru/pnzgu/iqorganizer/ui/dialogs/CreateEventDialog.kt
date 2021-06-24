package ru.pnzgu.iqorganizer.ui.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import ru.pnzgu.iqorganizer.R
import ru.pnzgu.iqorganizer.ui.calendar.recycler.FullEvent
import java.text.SimpleDateFormat
import java.util.*


class CreateEventDialog : DialogFragment() {
    companion object {
        const val TAG = "create_event_dialog"

        fun newInstance(): CreateEventDialog {
            return CreateEventDialog()
        }
    }

    private lateinit var toolbar: Toolbar

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setWindowAnimations(R.style.Theme_MaterialComponents_Light_Slide)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            R.style.Theme_MaterialComponents_Light_FullScreenDialog
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.event_dialog, container, false)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameEditText: EditText = view.findViewById(R.id.nameEditText)
        var name = ""
        nameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                if (name != s.toString())
                    name = s.toString()
            }
        })

        val infoEditText: EditText = view.findViewById(R.id.infoEditText)
        var info = ""
        infoEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                if (info != s.toString())
                    info = s.toString()
            }
        })

        val financesEditText: EditText = view.findViewById(R.id.financesEditText)
        var finances = 0.0
        financesEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                if (finances != s.toString().toDouble())
                    finances = s.toString().toDouble()
            }
        })

        val dateTime = Calendar.getInstance()

        val dateEditView: TextView = view.findViewById(R.id.dateEditView)
        dateEditView.text = SimpleDateFormat("dd.mm.yyyy", Locale.getDefault())
            .format(dateTime.time)
        val datePicker = object : DialogFragment(), DatePickerDialog.OnDateSetListener {
            override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
                val year = dateTime.get(Calendar.YEAR)
                val month = dateTime.get(Calendar.MONTH)
                val dayOfMonth = dateTime.get(Calendar.DAY_OF_MONTH)
                return DatePickerDialog(requireActivity(), this, year, month, dayOfMonth)
            }

            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                dateTime.set(Calendar.YEAR, year)
                dateTime.set(Calendar.MONTH, month)
                dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                dateEditView.text = SimpleDateFormat("dd.mm.yyyy", Locale.getDefault())
                    .format(dateTime.time)
            }
        }
        dateEditView.setOnClickListener {
            datePicker.show(childFragmentManager, "datePicker")
        }

        val timeEditView: TextView = view.findViewById(R.id.timeEditView)
        timeEditView.text = SimpleDateFormat("HH:mm", Locale.getDefault())
            .format(dateTime.time)
        val timePicker = object : DialogFragment(), TimePickerDialog.OnTimeSetListener {
            override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
                val hourOfDay = dateTime.get(Calendar.HOUR_OF_DAY)
                val minute = dateTime.get(Calendar.MINUTE)

                return TimePickerDialog(
                    requireActivity(), this, hourOfDay, minute, true
                )
            }

            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                dateTime.set(Calendar.MINUTE, minute)
                timeEditView.text = SimpleDateFormat("HH:mm", Locale.getDefault())
                    .format(dateTime.time)
            }
        }
        timeEditView.setOnClickListener {
            timePicker.show(childFragmentManager, "datePicker")
        }

        toolbar.setNavigationOnClickListener { dismiss() }
        toolbar.title = "Новое событие"
        toolbar.inflateMenu(R.menu.event_menu)

        toolbar.setOnMenuItemClickListener {
            val data = Bundle()

            val fullEvent = FullEvent(
                if (name.isBlank()) null else name,
                if (info.isBlank()) null else info,
                finances,
                dateTime.time,
                0,
                0
            )
            data.putParcelable("event", fullEvent)
            parentFragmentManager.setFragmentResult("create_event_result", data)
            dismiss()
            true
        }
    }
}